package org.team6.projectserver.model.primitive;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
public class ArchitectureInfo {

    @Getter @Setter
    String buildingName;

    @Getter @Setter
    String location;

    @Getter @Setter
    String builtYear;
}
