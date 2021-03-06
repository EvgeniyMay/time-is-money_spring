package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.ActivityDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.exception.ActivityAlreadyExistException;
import com.myLearning.timeIsMoney.exception.ActivityNotFountException;
import com.myLearning.timeIsMoney.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Page<Activity> getPageAbleByStatue(boolean isActive, Pageable pageable) {

        return activityRepository.findActivitiesByIsArchivedIs(!isActive, pageable);
    }

    public List<Activity> getActive() {
        return activityRepository.findActivitiesByIsArchivedIsFalse();
    }

    public boolean create(ActivityDTO activityDTO) {
        Activity activity = Activity.builder()
                .name(activityDTO.getName())
                .description(activityDTO.getDescription())
                .build();

        try {
            activityRepository.save(activity);
            return true;
        } catch (Exception e) {
            throw new ActivityAlreadyExistException("Activity already exists");
        }
    }

    public Activity getById(Long id) {
        return activityRepository.findById(id).orElseThrow(()->
                new ActivityNotFountException("Activity with id " + id + " not found"));
    }

    public boolean update(Activity activity) {
        try {
            activityRepository.save(activity);
            return true;
        } catch (Exception e) {
            throw new ActivityAlreadyExistException("Activity already exists");
        }
    }

    // ToDo | Refactor to one query
    @Transactional
    public void archiveById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        activity.setArchived(true);

        activityRepository.save(activity);
    }

    // ToDo | Refactor to one query
    @Transactional
    public void activateBtId(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        activity.setArchived(false);

        activityRepository.save(activity);
    }

}
