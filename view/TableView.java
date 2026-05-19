package mvc_exercise.view;

import mvc_exercise.model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableView {
    public static void UserViewTable(List<User> users){
        Table table = new Table(
                4,
                BorderStyle.UNICODE_BOX,
                ShownBorders.ALL
        );
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        String[] cols = {
                "UUID",
                "NAME",
                "EMAIL",
                "PROFILE"
        };

        for (String col : cols){
            table.addCell(col, center);
        }

        for (User user: users){
            table.addCell(user.getUuid(), center);
            table.addCell(user.getName(), center);
            table.addCell(user.getEmail(), center);
            table.addCell(user.getProfile(), center);
        }
        System.out.println(table.render());
    }
}
