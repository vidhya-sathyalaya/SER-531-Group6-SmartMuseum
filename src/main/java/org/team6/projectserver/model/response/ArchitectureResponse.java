package org.team6.projectserver.model.response;

import org.team6.projectserver.model.primitive.ArchitectureInfo;

import java.util.ArrayList;
import java.util.List;

public class ArchitectureResponse {
    List<ArchitectureInfo> results;

    public ArchitectureResponse(){
        results = new ArrayList<>();
    }

    public List<ArchitectureInfo> getResults(){
        return results;
    }
}
