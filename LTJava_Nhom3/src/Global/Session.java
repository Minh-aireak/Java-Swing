package Global;

import Logic.entity.TaiKhoan;

public class Session {
    private static TaiKhoan currentUser;

    public static TaiKhoan getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(TaiKhoan user) {
        currentUser = user;
    }
}
