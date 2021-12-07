package org.team6.projectserver.model.primitive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ArtistInfo {

    @Getter @Setter
    String name;

    @Getter @Setter
    String artistBio;

    @Getter @Setter
    String nationality;

    @Getter @Setter
    String gender;

    @Getter @Setter
    String born;

    @Getter @Setter
    String died;
}
