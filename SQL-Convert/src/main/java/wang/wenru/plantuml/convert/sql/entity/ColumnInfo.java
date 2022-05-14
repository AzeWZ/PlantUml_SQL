package wang.wenru.plantuml.convert.sql.entity;

import lombok.Data;

import java.util.List;

/**
 * Description
 * Date 2022/5/15 12:00 上午
 *
 * @author dafu
 */
@Data
public class ColumnInfo {

    private String columnName;


    private String dataType;

    private List<String> spec;
}
