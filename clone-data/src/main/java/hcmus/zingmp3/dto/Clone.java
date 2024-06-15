package hcmus.zingmp3.dto;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class Clone {
    private Set<String> cloned;
    private Set<String> toClone;

    public void addCloned(String id) {
        cloned.add(id);
    }

    public void addToClone(String id) {
        if (!cloned.contains(id)) {
            toClone.add(id);
        }
    }
}
