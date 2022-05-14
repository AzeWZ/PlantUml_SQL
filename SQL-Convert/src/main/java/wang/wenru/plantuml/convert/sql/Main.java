package wang.wenru.plantuml.convert.sql;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import wang.wenru.plantuml.convert.sql.entity.ColumnInfo;
import wang.wenru.plantuml.convert.sql.entity.TableInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 * Date 2022/5/14 11:51 下午
 *
 * @author dafu
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws JSQLParserException {
        String sql = "-- auto-generated definition\n" +
                "create table tb_login_method\n" +
                "(\n" +
                "    id                   bigint        not null comment '自增主键'\n" +
                "        primary key,\n" +
                "    login_method_id      bigint        not null comment '业务主键',\n" +
                "    login_method_account varchar(100)  not null comment '登录账号,union_id',\n" +
                "    login_method_secret  varchar(100)  null comment '密码, openId',\n" +
                "    account_id           bigint        null comment '账号表id',\n" +
                "    client_type          varchar(20)   null comment '编码标识类型 [主题][类型][程序编码]',\n" +
                "    create_time          datetime(6)   not null,\n" +
                "    update_time          datetime(6)   not null,\n" +
                "    deleted              int default 1 not null comment '删除标识'\n" +
                ");\n" +
                "\n";
        sql += sql;
        final String[] split = sql.split("\\);");
        for (String s : split) {
            try {
                System.out.println(writePlantUml(s+");"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static String writePlantUml(String sql) throws JSQLParserException {
        final Statement parse = CCJSqlParserUtil.parse(sql);
        if (CreateTable.class.equals(parse.getClass())) {
            TableInfo table = new TableInfo();

            //表信息
            CreateTable createTable = (CreateTable) parse;
            final Table tableDDL = createTable.getTable();
            table.setTableName(tableDDL.getName());
            table.setTableComment(table.getTableComment());

            //字段信息
            final List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
            final List<ColumnInfo> cols = columnDefinitions.stream().map(i -> {
                ColumnInfo column = new ColumnInfo();
                column.setColumnName(i.getColumnName());
                column.setDataType(i.getColDataType().getDataType());
                column.setSpec(i.getColumnSpecs());
                return column;
            }).collect(Collectors.toList());
            table.setColumns(cols);
            return (PlanUmlERWriter.writeTable(table));
        }
        return "";
    }
}
