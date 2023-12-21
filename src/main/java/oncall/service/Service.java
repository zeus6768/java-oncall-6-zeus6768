package oncall.service;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oncall.domain.calendar.OnCallCalendar;
import oncall.domain.calendar.OnCallDate;
import oncall.domain.calendar.OnCallDayOfWeek;
import oncall.domain.crew.Crew;
import oncall.domain.crew.Crews;
import oncall.domain.oncall.OnCallList;

public class Service {

    public OnCallCalendar createCalendar(List<String> monthAndDayOfWeek) {
        Month month = Month.of(Integer.parseInt(monthAndDayOfWeek.get(0)));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        DayOfWeek startDayOfWeek = OnCallDayOfWeek.findDayOfWeekByName(dayOfWeek);
        return new OnCallCalendar(month, startDayOfWeek);
    }

    public Crews createCrews(List<String> crewNames) {
        List<Crew> crews = crewNames.stream().map(Crew::new).toList();
        return new Crews(crews);
    }

    public OnCallList createOnCallList(OnCallCalendar calendar, Crews weekdayCrews, Crews holidayCrews) {
        List<Crew> weekdayCrewWithReplacement = getCrewsWithReplacement(weekdayCrews, calendar.countWeekdays());
        List<Crew> holidayCrewWithReplacement = getCrewsWithReplacement(holidayCrews, calendar.countWeekends());

        Map<OnCallDate, Crew> orderResult = new LinkedHashMap<>();

        Crew previousCrew = null;

        for (OnCallDate onCallDate : calendar.getOnCallDates()) {
            Crew crew;
            if (onCallDate.isWeekend() || onCallDate.isWeekdayAndHoliday()) {
                crew = getNextCrew(holidayCrewWithReplacement, previousCrew);
                orderResult.put(onCallDate, crew);
                previousCrew = crew;
                continue;
            }
            if (onCallDate.isWeekday()) {
                crew = getNextCrew(weekdayCrewWithReplacement, previousCrew);
                orderResult.put(onCallDate, crew);
                previousCrew = crew;
            }
        }
        return new OnCallList(orderResult);
    }

    private List<Crew> getCrewsWithReplacement(Crews crews, int size) {
        List<Crew> result = new ArrayList<>();
        int index = 0;
        while (result.size() < size) {
            Crew crew = crews.get(index);
            result.add(crew);
            index = (index + 1) % crews.size();
        }
        return result;
    }

    private Crew getNextCrew(List<Crew> crews, Crew previousCrew) {
        Crew crew = crews.get(0);
        if (crew.equals(previousCrew)) {
            swapFirstTwoCrews(crews);
        }
        return crews.remove(0);
    }

    private void swapFirstTwoCrews(List<Crew> crews) {
        if (crews == null || crews.size() <= 1) {
            throw new IllegalArgumentException("비상 근무일을 배치할 수 없습니다.");
        }
        Crew temp = crews.get(0);
        crews.set(0, crews.get(1));
        crews.set(1, temp);
    }
}
