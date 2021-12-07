package org.team6.projectserver.model.response;

import lombok.Getter;
import lombok.Setter;
import org.team6.projectserver.model.primitive.ArtInfo;

import java.util.ArrayList;
import java.util.List;

public class ArtInfoResponse {

    @Getter
    List<ArtInfo> results;

    public ArtInfoResponse(){
        results = new ArrayList<ArtInfo>();
    }
}
