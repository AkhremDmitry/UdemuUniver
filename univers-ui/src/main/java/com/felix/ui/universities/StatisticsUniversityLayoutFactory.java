package com.felix.ui.universities;

import com.felix.model.entity.University;
import com.felix.service.university.ShowAllUniversitiesService;
import com.felix.service.university.UniversityStatisticsService;
import com.felix.ui.commons.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class StatisticsUniversityLayoutFactory implements UIComponentBuilder {

    private List<University> universities;
    private StatisticsUniversityLayout statisticsUniversityLayout;

    @Autowired
    private ShowAllUniversitiesService showAllUniversitiesService;

    @Autowired
    private UniversityStatisticsService universityStatisticsService;

    private class StatisticsUniversityLayout extends VerticalLayout{

        public StatisticsUniversityLayout load() {
            universities = showAllUniversitiesService.getAllUniversities();
            return this;
        }

        public StatisticsUniversityLayout layout() {
            setMargin(true);

            for(University university : universities){
                int numOfStudents = universityStatisticsService.getNumberOfStudentsForUniversity(university.getId());
                Label label = new Label("<p><b>"+university.getUniversityName()+"</b>"+"  -  "
                        +numOfStudents+" student(s)"+"</p>", ContentMode.HTML);
                addComponent(label);
            }
            return this;
        }
    }

    public void refresh() {

        if (statisticsUniversityLayout==null){
            return;
        }

        statisticsUniversityLayout.removeAllComponents();
        statisticsUniversityLayout.load().layout();
    }

    public Component createComponent() {
        statisticsUniversityLayout = new StatisticsUniversityLayout().load().layout();
        return statisticsUniversityLayout;
    }
}
