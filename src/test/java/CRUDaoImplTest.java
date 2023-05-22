
import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;
import com.example.budget.service.CRUDaoImpl;
import com.example.budget.service.HibernateRunner;
import org.junit.jupiter.api.Test;


import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CRUDaoImplTest {
    CRUDaoImpl cruDaoImpl = new CRUDaoImpl(HibernateRollBack.create(HibernateRunner.getSessionFactory()));
    @Test
    void add() {
        CRUDaoImpl cruDao = new CRUDaoImpl(HibernateRunner.getSessionFactory());
        BalanceObject salary = new BalanceObject.Builder()
                .name("salary")
                .date(Date.valueOf(LocalDate.now()))
                .amount(2.22)
                .type(BalanceType.EXPENSE)
                .build();
        assertThat(cruDaoImpl.add(salary).getName(), is("salary"));
    }
    @Test
    void select() {
        CRUDaoImpl cruDao = new CRUDaoImpl(HibernateRunner.getSessionFactory());
        BalanceObject salary = new BalanceObject.Builder()
                .name("salary")
                .date(Date.valueOf(LocalDate.now()))
                .amount(2.22)
                .type(BalanceType.EXPENSE)
                .build();
        cruDaoImpl.add(salary);
        assertThat(cruDaoImpl.select().size(), is(1) );
    }
    @Test
    void update() {
        CRUDaoImpl cruDao = new CRUDaoImpl(HibernateRunner.getSessionFactory());
        BalanceObject salary = new BalanceObject.Builder()
                .name("salary")
                .date(Date.valueOf(LocalDate.now()))
                .amount(2.22)
                .type(BalanceType.EXPENSE)
                .build();
        assertThat(cruDaoImpl.update(salary).getName(),is("masha"));
    }
    @Test
    void delete() {
        CRUDaoImpl cruDao = new CRUDaoImpl(HibernateRunner.getSessionFactory());
        BalanceObject salary = new BalanceObject.Builder()
                .name("salary")
                .date(Date.valueOf(LocalDate.now()))
                .amount(2.22)
                .type(BalanceType.EXPENSE)
                .build();
        cruDaoImpl.add(salary);
        cruDaoImpl.delete(salary);
       assertThat(cruDaoImpl.select().size(), is(0));
    }
}