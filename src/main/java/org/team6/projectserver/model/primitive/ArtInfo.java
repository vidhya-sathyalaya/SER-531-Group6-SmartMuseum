package org.team6.projectserver.model.primitive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ArtInfo {

    @Getter @Setter
    String title;

    @Getter @Setter
    String year;

    @Getter @Setter
    String medium;

    @Getter @Setter
    String classification;

    @Getter @Setter
    String department;

}
