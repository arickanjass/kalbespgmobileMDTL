package addons.tableview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.kalbenutritionals.app.kalbespgmobile.R;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import library.spgmobile.common.ReportTable;

/**
 * Created by Dewi Oktaviani on 05/09/2017.
 */

public class SortabeReportTableViewDetail extends SortableTableView<ReportTable> {
    public SortabeReportTableViewDetail(final Context context) {
        this(context, null);
    }

    public SortabeReportTableViewDetail(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public SortabeReportTableViewDetail(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);

        final SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.table_header_text));

        setHeaderAdapter(simpleTableHeaderAdapter);
        SortabeReportTableViewDetail tableQuiz = (SortabeReportTableViewDetail) findViewById(R.id.tableViewQuis);
        tableQuiz.setColumnCount(0);
        final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        setColumnWeight(0, 0);
    }
}
