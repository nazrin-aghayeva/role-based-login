package com.employee.app.services;

import com.employee.app.entities.Workers;
import com.employee.app.repos.WorkerRepo;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    public WorkerRepo workerRepo;

    public WorkerService( WorkerRepo workerRepo){
        this.workerRepo= workerRepo;
    }
    public Workers findWorkerByName(String worker_name){
       return workerRepo.findByFull_name(worker_name).orElseThrow(RuntimeException::new);
    }

    public Workers createWorker(Workers worker){
        return workerRepo.save(worker);
    }
}
