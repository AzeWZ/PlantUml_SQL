package wang.wenru.plantuml.convert.sql;

import wang.wenru.plantuml.convert.sql.entity.ColumnInfo;
import wang.wenru.plantuml.convert.sql.entity.TableInfo;

import java.util.List;

/**
 * Description
 * Date 2022/5/15 12:10 上午
 *
 * @author dafu
 */
public class PlanUmlERWriter {

    public static String writeTable(TableInfo table){
        StringBuilder sb = new StringBuilder();
        sb.append("entity "+table.getTableName()+" {\n");
        sb.append("--comment--\n");
        sb.append(table.getTableComment() + "\n");
        final List<ColumnInfo> columns = table.getColumns();
        sb.append("--columns--\n");
        columns.forEach(i->{
            sb.append(i.getColumnName()+": "+i.getDataType()+" ");
            if (null != i.getSpec() && i.getSpec().size() > 0) {
                for (String s : i.getSpec()) {
                    sb.append(s+" ");
                }
            }
            sb.append("\n");
        });
        sb.append("}");
        return sb.toString();
    }
//    entity "resume_basic 简历库信息表" as resume_basic{
//        id: 主键/bigint(20) unsigned
//        resume_name: 简历文件名/varchar(100)
//        chinese_name: 简历人员姓名姓名/varchar(30)
//        hand_phone: 应聘人员电话/varchar(30)
//        email_account: 个人邮箱/varchar(100)
//        status: 简历状态/0/tinyint(4)
//        work_status:应聘工作状态/tinyint(4)
//        resource: 简历来源/tinyint(4)
//        create_time:录入时间/timestamp
//        date_create:创建时间/timestamp
//        date_update:修改时间/timestamp
//        update_user_id:修改人/bigint(20)
//        create_user_id:创建人/bigint(20)
//                --key extend --
//                PKey:id
//        Key:chinese_name
//        Key:hand_phone
//        UKey:chinese_name,hand_phone
//                --comment--
//        备注信息
//    }
}
