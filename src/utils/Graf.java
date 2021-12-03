package utils;

import domain.Utilizator;
import repository.file.AbstractFileRepository;

import java.util.*;

public class Graf {

    private final Map<Long, List<Long>> adjVertices = new HashMap<>();
    private List<Long> keyList;

    public Graf(AbstractFileRepository<Long, Utilizator> repository) {
        createGraf(repository);
    }

    public void addVarf(Long id) {
        adjVertices.putIfAbsent(id, new ArrayList<>());
    }

    public List<Long> getAdjVertices(Long id) {
        return adjVertices.get(id);
    }

    public void addEdge(Long id1, Long id2) {
        adjVertices.get(id1).add(id2);
        adjVertices.get(id2).add(id1);
    }

    public void createGraf(AbstractFileRepository<Long, Utilizator> repository) {
        keyList = new ArrayList<Long>(repository.getEntities().keySet());

        for (Long key : keyList) {
            addVarf(key);
        }
        for (Utilizator utilizator : repository.getEntities().values()) {
            List<Utilizator> friends = utilizator.getFriends();
            for (Utilizator utilizator1 : friends) {
                addEdge(utilizator.getId(), utilizator1.getId());
            }
        }
    }

    public Set<Long> breadthFirstTraversal(Long root) {
        Set<Long> visited = new LinkedHashSet<>();
        Queue<Long> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Long vertex = queue.poll();
            for (Long v : getAdjVertices(vertex)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return visited;
    }

    public Set<Set<Long>> poolOfConnectedComponents() {
        Set<Set<Long>> stringSet = new HashSet<>();
        ArrayList<String> stringBuilder = new ArrayList<>();
        for (Long key : keyList) {
            stringBuilder.add(key.toString());
        }
        for (String s : stringBuilder) {
            stringSet.add(this.breadthFirstTraversal(Long.parseLong(s)));
        }
        return stringSet;
    }

    public int getNrOfConnectedComponents() {
        Set<Set<Long>> stringSet = poolOfConnectedComponents();
        return stringSet.size();
    }

    public Set<Long> getLargestConnectedComponent() {
        int max = 0;
        Set<Long> setMax = new HashSet<>();
        Set<Set<Long>> stringSet = poolOfConnectedComponents();
        for (Set<Long> set : stringSet) {
            if (set.size() > max) {
                max = set.size();
                setMax = set;
            }
        }
        return setMax;
    }
}
