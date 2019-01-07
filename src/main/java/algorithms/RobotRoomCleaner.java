package algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class RobotRoomCleaner {
    private List<Dir> find(Node start) {
        Queue<ExploreNode> queue = new LinkedList<>();
        queue.offer(new ExploreNode(start, null, null));
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            ExploreNode curr = queue.poll();

            if (visited.contains(curr.node)) continue;
            visited.add(curr.node);

            if (curr.node.isUnexplored()) {
                LinkedList<Dir> toUnexplored = new LinkedList<>();
                ExploreNode tmp = curr;
                while (curr.prev != null) {
                    toUnexplored.add(tmp.dir);
                    tmp = tmp.prev;
                }
                return toUnexplored;
            }
            for (Map.Entry<Dir, Node> entry : curr.node.dirs.entrySet()) {
                if (entry.getValue() != null) {
                    queue.offer(new ExploreNode(entry.getValue(), entry.getKey(), curr));
                }
            }
        }
        return new LinkedList<>();
    }

    public void cleanRoom(Robot robot) {
        if (robot == null) return;

        Node first = new Node();
        RobotState rstate = new RobotState(robot, Dir.UP, first);
        List<Dir> toUnexplored = null;
        do {
            rstate.explore();
            toUnexplored = find(rstate.node);
            for (Dir dir : toUnexplored) {
                rstate.move(dir);
                rstate.clean();
            }
        } while (!toUnexplored.isEmpty());
    }

    private static enum Dir {

        UP, DOWN, LEFT, RIGHT;

        static {
            UP.clockwise = RIGHT;
            UP.opposite = DOWN;

            DOWN.clockwise = LEFT;
            DOWN.opposite = UP;

            LEFT.clockwise = UP;
            LEFT.opposite = RIGHT;

            RIGHT.clockwise = DOWN;
            RIGHT.opposite = LEFT;
        }

        Dir clockwise;
        Dir opposite;
    }

    static class Node {
        Map<Dir, Node> dirs = new HashMap<>();
        boolean cleaned;
        Set<Dir> unexplored = new HashSet<>();

        Node() {
            unexplored.addAll(Arrays.asList(Dir.values()));
        }

        boolean isUnexplored() {
            return unexplored.size() > 0;
        }

        void explore(boolean moved, Dir dir) {
            if (!unexplored.contains(dir)) return;
            unexplored.remove(dir);
            if (moved) {
                dirs.put(dir, new Node());
            }
        }

        Node get(Dir dir) {
            return dirs.get(dir);
        }
    }

    static class ExploreNode {
        Node node;
        Dir dir;
        ExploreNode prev;

        ExploreNode(Node node, Dir dir, ExploreNode prev) {
            this.node = node;
            this.dir = dir;
            this.prev = prev;
        }
    }

    static class Robot {

        public boolean move() {
            return false;
        }

        public void clean() {

        }

        public void turnRight() {

        }
    }

    static class RobotState {

        Robot robot;
        Dir dir;
        Node node;

        RobotState(Robot robot, Dir dir, Node node) {
            this.robot = robot;
            this.dir = dir;
            this.node = node;
        }

        void explore() {
            if (node.isUnexplored()) {
                Dir prevDir = dir;
                for (Dir currDir : Dir.values()) {
                    turn(currDir);
                    boolean moved = robot.move();
                    node.explore(moved, currDir);
                    if (moved) {
                        turn(currDir.opposite);
                        robot.move();
                    }
                }
                turn(prevDir);
            }
        }

        void clean() {
            robot.clean();
            node.cleaned = true;
        }

        void move(Dir nextDir) {
            turn(nextDir);
            moveTo(nextDir);
        }

        void turn(Dir nextDir) {
            while (nextDir != dir) {
                robot.turnRight();
                dir = dir.clockwise;
            }
        }

        void moveTo(Dir nextDir) {
            node = node.get(nextDir);
            robot.move();
        }
    }
}