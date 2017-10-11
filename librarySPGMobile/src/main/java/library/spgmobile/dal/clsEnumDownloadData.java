package library.spgmobile.dal;

/**
 * Created by aan.junianto on 3/10/2017.
 */

public enum clsEnumDownloadData {
    ll_absen (1),
    ll_activity (2),
    ll_branch (3),
    ll_brand (4),
    ll_CategoryKoordinasiOutlet (5),
    ll_customerbased (6),
    ll_data_activity (7),
    ll_data_activityV2 (8),
    ll_data_addDisplay (9),
    ll_data_attendance (10),
    ll_data_customerbased (11),
    ll_data_leave (12),
    ll_data_overStock (13),
    ll_data_planogram (14),
    ll_data_reso (15),
    ll_data_stockIH (16),
    ll_dataKordinasiOutlet (17),
    ll_dataPurchaseOrder (18),
    ll_dataQuantityStock (19),
    ll_dataQuesioner (20),
    ll_dataTrackingLocationMobile (21),
    ll_dataVisitPlan (22),
    ll_kategoriVisitPlan (23),
    ll_kategoryPlanogram (24),
    ll_outlet (25),
    ll_product (26),
    ll_product_competitor (27),
    ll_product_pic (28),
    ll_product_SPG (29),
    ll_purchase_order (30),
    ll_reso (31),
    ll_subtypeactivity (32),
    ll_type_leave (33),
    ll_type_submission (34),
    ll_dataSPG (35);

    clsEnumDownloadData(int idData) {this.idData = idData;}
    public int getidCounterData() {
        return this.idData;
    }
    private  final int idData;
}

