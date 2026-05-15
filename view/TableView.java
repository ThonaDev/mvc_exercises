package mvc_exercise.view;

import mvc_exercise.model.dto.UserResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableView {

    public static void renderUserTable(List<UserResponseDto> users) {

        Table table = new Table(
                4,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                ShownBorders.ALL
        );

        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        // Headers
        table.addCell("UUID", centerStyle);
        table.addCell("NAME", centerStyle);
        table.addCell("EMAIL", centerStyle);
        table.addCell("PROFILE", centerStyle);

        // Rows (FIXED: apply style here too)
        for (UserResponseDto user : users) {
            table.addCell(user.uuid(), centerStyle);
            table.addCell(user.name(), centerStyle);
            table.addCell(user.email(), centerStyle);
            table.addCell(user.profile(), centerStyle);
        }

        System.out.println(table.render());
    }
}