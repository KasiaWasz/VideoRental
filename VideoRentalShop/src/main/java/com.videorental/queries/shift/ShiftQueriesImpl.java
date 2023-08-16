package com.videorental.queries.shift;

import com.videorental.dtos.shift.ShiftDto;
import com.videorental.dtos.shift.ShiftSimpleDto;
import com.videorental.entities.shift.Shift;
import com.videorental.queries.AbstractQueries;
import com.videorental.queries.employee.EmployeeQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Service
class ShiftQueriesImpl extends AbstractQueries<Shift> implements ShiftQueries {

    private final EmployeeQueries employeeQueries;

    private final ShiftSimpleDtoFactory shiftSimpleDtoFactory;


    @Autowired
    ShiftQueriesImpl(SessionFactory sessionFactory, EmployeeQueries employeeQueries, ShiftSimpleDtoFactory shiftSimpleDtoFactory) {

        super(sessionFactory, Shift.class);

        Assert.notNull(employeeQueries, "employeeQueries must not be null");
        Assert.notNull(shiftSimpleDtoFactory, "shiftDtoFactory must not be null");

        this.employeeQueries = employeeQueries;
        this.shiftSimpleDtoFactory = shiftSimpleDtoFactory;
    }

    public List<ShiftDto> getAllShiftDto() {

        return getAll().stream()
                .map(shift -> new ShiftDto(
                        shift.getId(),
                        shift.getEmployeeId(),
                        employeeQueries.getEmployeeDetailDtoById(shift.getEmployeeId()).getFirstName(),
                        employeeQueries.getEmployeeDetailDtoById(shift.getEmployeeId()).getLastName(),
                        shift.getDate(),
                        shift.getHours()
                ))
                .toList();
    }

    public List<ShiftSimpleDto> getShiftsByEmployeeId(Long employeeId) {

        Assert.notNull(employeeId, "employeeId must not be null");

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Shift> cq = cb.createQuery(Shift.class);
            Root<Shift> root = cq.from(Shift.class);

            cq.select(root)
                    .where(cb.equal(root.get("employeeId"), employeeId));

            return session.createQuery(cq)
                    .getResultList().stream()
                    .map(shiftSimpleDtoFactory::create)
                    .toList();
        }
    }

    public List<ShiftSimpleDto> getShiftsByDate(LocalDate startDate, LocalDate endDate) {

        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Shift> cq = cb.createQuery(Shift.class);
            Root<Shift> root = cq.from(Shift.class);

            cq.select(root)
                    .where(
                        cb.greaterThanOrEqualTo(root.get("date"), startDate),
                        cb.lessThanOrEqualTo(root.get("date"), endDate));

            return session.createQuery(cq)
                    .getResultList().stream()
                    .map(shiftSimpleDtoFactory::create)
                    .toList();
        }
    }
}
