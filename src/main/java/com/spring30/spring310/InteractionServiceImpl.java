package com.spring30.spring310;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements InteractionService{
    
    private InteractionRepository interactionRepository;

    private CustomerRepository customerRepository;


    // Constructor injection so don't need @Autowired
    public InteractionServiceImpl(InteractionRepository interactionRepository, CustomerRepository customerRepository) {
        this.interactionRepository = interactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Interaction createInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    @Override
    public Interaction getInteraction(int id) {

        Optional<Interaction> interaction = interactionRepository.findById(id);
        if(interaction.isPresent()){
            return interaction.get();
        }else{
            throw new InteractionNotFoundException(id);
        }
    }

    @Override
    public ArrayList<Interaction> getAllInteractions() {
        List<Interaction> allInteractions = interactionRepository.findAll();
        return (ArrayList<Interaction>) allInteractions;
    }

    @Override
    public Interaction updateInteraction(int id, Interaction interaction) {
        Optional<Interaction> wrappedInteractionToUpdate = interactionRepository.findById(id);

    if (!wrappedInteractionToUpdate.isPresent()) {
      throw new InteractionNotFoundException(id);
    }

    Interaction interactionToUpdate = wrappedInteractionToUpdate.get();

    interactionToUpdate.setRemarks(interaction.getRemarks());
    interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
    return interactionRepository.save(interactionToUpdate);
    }

    @Override
    public void deleteInteraction(int id) {
        interactionRepository.deleteById(id);
    }

   


}
