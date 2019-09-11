package Dormitory;

public interface InterfaceSet {
    public boolean login(String username, String password);

    public String getPassword(String username);

    public void updatePassword(String username, String password);
}