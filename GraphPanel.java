import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GraphPanel extends JPanel implements MouseListener, MouseMotionListener{
    private int vertices;
    private Node[] nodes;
    private LinkedList<Integer> order = new LinkedList<Integer>();
    private Stack<Node> stack = new Stack<Node>();
    private Queue<Node> queue = new LinkedList<Node>();
    private int radius = 20;
    private int[] neighbors;
    private boolean[] visited;
    private boolean found = false;
    private boolean finish = false;
    private int start;
    private int end;
    private int lastExploredNeighbor = -1;
    private Node drag = null;
    public int dfs_or_bfs;

    public GraphPanel (int vertices){
        this.vertices = vertices;
        nodes = new Node[vertices];
        visited = new boolean[vertices];
        neighbors = new int[vertices];
        nodes[0] = new Node(0, 300, 110);
        nodes[1] = new Node(1, 400, 140);
        nodes[2] = new Node(2, 450, 220);
        nodes[3] = new Node(3, 480, 300);
        nodes[4] = new Node(4, 450, 380);
        nodes[5] = new Node(5, 380, 460);
        nodes[6] = new Node(6, 300, 480);
        nodes[7] = new Node(7, 210, 450);
        nodes[8] = new Node(8, 150, 380);
        nodes[9] = new Node(9, 120, 300);
        nodes[10] = new Node(10, 150, 220);
        nodes[11] = new Node(11, 220, 140);
        setSize(1200, 800);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void add_edge(int node1,int node2){
        nodes[node1].add_adjacency(nodes[node2]);
        nodes[node2].add_adjacency(nodes[node1]);
        repaint();
    }

    public void remove_edge(int node1,int node2){
        nodes[node1].remove_adjacency(nodes[node2]);
        nodes[node2].remove_adjacency(nodes[node1]);
        repaint();
    }

    public Node[] get_nodes() {
        return nodes;
    }
    public void set_nodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public void clear(){
        nodes = new Node[vertices];
        order.clear();
        Arrays.fill(visited,false);
        Arrays.fill(neighbors,0);
        stack.clear();
        found = false;
        finish = false;
        repaint();
    }

    public void reset(){
        stack.clear();
        Arrays.fill(neighbors,0);
        Arrays.fill(visited,false);
        order.clear();
        found = false;
        finish = false;
        queue.clear();
    }

    public void random_edge(int vertices){
        Random rand = new Random();
        for (int i = 0; i< vertices;i++){
            for (int j = i+1; j < vertices; j++){
                if (rand.nextInt(4) == 0){
                    add_edge(i,j);
                }
            }
        }
    }

    public void generate_random(){
        clear();

        nodes[0] = new Node(0, 300, 110);
        nodes[1] = new Node(1, 400, 140);
        nodes[2] = new Node(2, 450, 220);
        nodes[3] = new Node(3, 480, 300);
        nodes[4] = new Node(4, 450, 380);
        nodes[5] = new Node(5, 380, 460);
        nodes[6] = new Node(6, 300, 480);
        nodes[7] = new Node(7, 210, 450);
        nodes[8] = new Node(8, 150, 380);
        nodes[9] = new Node(9, 120, 300);
        nodes[10] = new Node(10, 150, 220);
        nodes[11] = new Node(11, 220, 140);

        random_edge(vertices);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (Node i : nodes) {
            if (i != null) {
                for (Node j : i.get_adjacency()) {
                    g.drawLine(i.get_x(), i.get_y(), j.get_x(), j.get_y());
                }
            }
        }

        for (Node i : nodes) {
            if (i != null) {
                int id = i.get_id();
                if (!stack.isEmpty() && id == stack.peek().get_id() || dfs_or_bfs == 1 && id == lastExploredNeighbor) {
                    g.setColor(Color.GREEN);
                } else if (!queue.isEmpty() && id == queue.peek().get_id()) {
                    g.setColor(Color.ORANGE);
                } else if (order.contains(id)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillOval(i.get_x() - radius, i.get_y() - radius, 2 * radius, 2 * radius);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(id), i.get_x()-2, i.get_y()+5);
            }
        }

        if (dfs_or_bfs == 0) {
            g.setColor(Color.BLACK);
            g.drawString("Stack Representation:", getWidth() - 225, getHeight() - 80);
            int stack_tos = getHeight() - 100;
            for (Node temp : stack) {
                if (temp != null) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(getWidth() - 200, stack_tos - 30, 60, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(getWidth() - 200, stack_tos - 30, 60, 30);
                    g.drawString(String.valueOf(temp.get_id()), getWidth() - 175, stack_tos - 10);
                    stack_tos -= 35;
                }
            }
        }

        if (dfs_or_bfs == 1) {
            g.setColor(Color.BLACK);
            g.drawString("Queue Representation:", getWidth() - 225, getHeight() - 80);
            int queue_tos = getHeight() - 100;
            for (Node temp : queue) {
                if (temp != null) {
                    g.setColor(Color.CYAN);
                    g.fillRect(getWidth() - 200, queue_tos - 30, 60, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(getWidth() - 200, queue_tos - 30, 60, 30);
                    g.drawString(String.valueOf(temp.get_id()), getWidth() - 175, queue_tos - 10);
                    queue_tos -= 35;
                }
            }
        }

        if (found) {
            g.drawString("Target found. Path exists between " + start + " and " + end, 400, 700);
        } else if (finish) {
            g.drawString("Target not found. No path exists between " + start + " and " + end, 400, 700);
        }
    }


    public void mousePressed(MouseEvent e) {
        Point mousePos = e.getPoint();
        for (Node node : nodes) {
            if (node != null && mousePos.distance(node.get_x(), node.get_y()) <= radius) {
                drag = node;
                break;
            }
        }
    }


    public void mouseReleased(MouseEvent e) {
        drag = null;
    }


    public void mouseDragged(MouseEvent e) {
        if (drag != null) {
            drag.set_x(e.getX());
            drag.set_y(e.getY());
            repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void startDFS(int start_vertex, int end_vertex) {
        dfs_or_bfs = 0;
        reset();
        this.start = start_vertex;
        this.end = end_vertex;
        stack.push(nodes[start_vertex]);
        visited[start_vertex] = true;
        order.add(start_vertex);

        javax.swing.Timer dfsTimer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!stack.isEmpty()) {
                    Node current = stack.peek();
                    if (current.get_id() == end_vertex) {
                        found = true;
                        ((javax.swing.Timer) e.getSource()).stop();
                    } else {
                        LinkedList<Node> adjacency = current.get_adjacency();
                        boolean unvisited_adjacency = false;

                        for (int i = neighbors[current.get_id()]; i < adjacency.size(); i++) {
                            Node neighbor = adjacency.get(i);
                            neighbors[current.get_id()] = i + 1;
                            if (!visited[neighbor.get_id()]) {
                                stack.push(neighbor);
                                visited[neighbor.get_id()] = true;
                                order.add(neighbor.get_id());
                                unvisited_adjacency = true;
                                break;
                            }
                        }

                        if (!unvisited_adjacency) {
                            stack.pop();
                        }
                    }
                } else {
                    finish = true;
                    ((javax.swing.Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        dfsTimer.start();
    }

    public void startBFS(int start_vertex, int end_vertex) {
        dfs_or_bfs = 1;
        reset();
        this.start = start_vertex;
        this.end = end_vertex;

        queue.add(nodes[start_vertex]);
        visited[start_vertex] = true;
        order.add(start_vertex);

        javax.swing.Timer bfsTimer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    Node current = queue.peek();
                    boolean addedNewNode = false;

                    for (Node neighbor : current.get_adjacency()) {
                        if (!visited[neighbor.get_id()]) {

                            queue.add(neighbor);
                            visited[neighbor.get_id()] = true;
                            order.add(neighbor.get_id());
                            lastExploredNeighbor = neighbor.get_id();
                            addedNewNode = true;

                            if (neighbor.get_id() == end_vertex) {
                                found = true;
                                ((javax.swing.Timer) e.getSource()).stop();
                            }

                            break;
                        }
                    }

                    if (!addedNewNode) {
                        queue.poll();
                        lastExploredNeighbor = -1;
                    }

                } else {
                    finish = true;
                    ((javax.swing.Timer) e.getSource()).stop();
                }

                repaint();
            }
        });

        bfsTimer.start();
    }

}