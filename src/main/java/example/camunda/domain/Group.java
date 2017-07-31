package example.camunda.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ashlah on 28/07/17.
 */
@Entity
@Table(name = "`group`")
@Setter @Getter
public class Group implements org.camunda.bpm.engine.identity.Group {

    @Id
//    @Column(columnDefinition = "char(36)")
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`type`")
    private String type;
}
