package org.team6.projectserver.model.response;

import lombok.Getter;
import lombok.Setter;
import org.team6.projectserver.model.primitive.ArtistInfo;

import java.util.ArrayList;
import java.util.List;

public class ArtistInfoResponse {

    @Getter
    List<ArtistInfo> results;

    public ArtistInfoResponse(){
        results = new ArrayList<ArtistInfo>();
    }
}
