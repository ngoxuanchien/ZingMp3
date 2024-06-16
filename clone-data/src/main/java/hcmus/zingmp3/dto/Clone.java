package hcmus.zingmp3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Clone {
    private Queue<String> cloned;
    private Queue<String> toClone;

    public Clone() {
        cloned = new LinkedList<>();
        toClone = new LinkedList<>();
    }

    public void addCloned(String id) {
        cloned.add(id);
    }

    public void addToClone(String id) {
        id = id.replace("\"", "");
        if (!cloned.contains(id)) {
            toClone.add(id);
        }
    }
}
