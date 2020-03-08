package javaguitableandconnectonpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class BankDao extends ConnectionPool implements Dao<Bank> {

    public BankDao(String URL, String USERNAME, String PASSWORD) throws SQLException {
        super(URL, USERNAME, PASSWORD);
    }

    /*
        private final String name;
    private final String surname;
    private final Integer balance;
    private final String address;
    private final String gender;
    private final Date birthday;
     */
    @Override
    public void save(Bank entity) throws SQLException {
        ConnectionPool pool = new ConnectionPool(URL, USERNAME, PASSWORD);
        Connection conn = pool.getConnection();
        System.out.println(pool.getFreeConnectionCount());
        String sqlInsertStatement = "INSERT INTO accountservice ( `name`, `surname`, `balance`, `address`,`gender`, `birthday`)\n"
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement statement = pool.getConnection().prepareStatement(sqlInsertStatement);

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setDouble(3, entity.getBalance());
        statement.setString(4, entity.getAddress());
        statement.setString(5, entity.getGender());
        statement.setDate(6, entity.getBirthday());

        statement.executeUpdate();
        System.out.println(pool.getFreeConnectionCount());
    }

    @Override
    public List<Bank> getAll() throws SQLException {
        List<Bank> bankList = new ArrayList<>();
        try {
              
            ConnectionPool pool1 = new ConnectionPool(URL, USERNAME, PASSWORD);
               System.out.println(pool1.getFreeConnectionCount());
            Statement st = pool1.getConnection().createStatement();
            String sqlGetAllStatement = "SELECT * FROM banksystemdata.accountservice";

            ResultSet customerList = st.executeQuery(sqlGetAllStatement);
            Bank bank;
            while (customerList.next()) {
                bank = new Bank(customerList.getInt("id"), customerList.getString("name"), customerList.getString("surname"), customerList.getDouble("balance"), customerList.getString("address"),
                        customerList.getString("gender"), customerList.getDate("birthday"));
                bankList.add(bank);
                    System.out.println(pool1.getFreeConnectionCount());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }finally{
             System.out.println(getFreeConnectionCount());
        }
        return bankList;

    }

    @Override
    public Bank get(int primaryKey) throws SQLException {
        ConnectionPool pool2 = new ConnectionPool(URL, USERNAME, PASSWORD);
        String sqlQuery = "SELECT *FROM accountservice WHERE id=?";
        PreparedStatement preparedStatement = pool2.getConnection().prepareStatement(sqlQuery);
        preparedStatement.setInt(1, primaryKey);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return new Bank(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDate(6));
        } else {
            return null;
        }

    }

    @Override
    public void update(Bank entity) throws SQLException {
        try {
            ConnectionPool pool3 = new ConnectionPool(URL, USERNAME, PASSWORD);
            String sqlStatement = "UPDATE accountservice SET  `name`=?, `surname`=?,`balance`=?,`address`=?, `gender`=?, `birthday`=? WHERE id=?";
            PreparedStatement ps = pool3.getConnection().prepareStatement(sqlStatement);
            //ResultSet rs = ps.executeUpdate(sqlStatement);
            //  ps.setInt(0, entity.getId());
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getSurname());
            ps.setDouble(3, entity.getBalance());
            ps.setString(4, entity.getAddress());
            ps.setString(5, entity.getGender());
            ps.setDate(6, entity.getBirthday());
            ps.setInt(7, entity.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
 

    @Override
    public void delete(Bank entity) throws SQLException {
        try {
            ConnectionPool pool4 = new ConnectionPool(URL, USERNAME, PASSWORD);
            String sqlStatement = "delete from accountservice WHERE id=? ";
            PreparedStatement ps = pool4.getConnection().prepareStatement(sqlStatement);
            ps.setInt(1, entity.getId());
        

            ps.executeUpdate();
            
            
        /*    int updated_rows = 0;
            if (updated_rows > 0) {
                System.out.println("DELETED SUCCESSFULLY");
            } else {
                System.out.println("YOU FAILED!");
            }
*/
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
