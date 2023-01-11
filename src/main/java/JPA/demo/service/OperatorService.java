package JPA.demo.service;

import JPA.demo.entity.Operators;
import JPA.demo.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public Operators getOperatorById(int id) {
        return operatorRepository.findById(id).orElse(null);
    }
    public List<Operators> getOperators() {
        return operatorRepository.findAll();
    }
}
