package org.bee.jpalearngin.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
    @AttributeOverride( name = "name", column = @Column(name = "guardian_name", nullable = false, columnDefinition = "TEXT")),
    @AttributeOverride( name = "email", column = @Column(name = "guardian_email", nullable = false, columnDefinition = "TEXT")),
    @AttributeOverride( name = "mobile", column = @Column(name = "guardian_mobile", nullable = false))
})
public class Guardian {

    private String name;
    private String email;
    private String mobile;
}
