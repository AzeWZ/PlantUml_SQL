package wang.wenru.plantuml.convert.sql.entity;

import lombok.Data;

import java.util.List;

/**
 * Description
 * Date 2022/5/14 11:59 下午
 *
 * @author dafu
 */
@Data
public class TableInfo {
    private String tableName;

    private String tableComment;

    private List<ColumnInfo> columns;
}
