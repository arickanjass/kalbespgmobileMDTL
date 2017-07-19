package library.spgmobile.dal;

/**
 * Created by aan.junianto on 12/07/2017.
 */

public enum enumRole {
    MDMobile  (123);

    enumRole(int roleId) {
        this.roleId = roleId;
    }
    public int getRoleId() {
        return this.roleId;
    }
    private  final int roleId;
}
