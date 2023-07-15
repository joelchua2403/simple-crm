package com.spring30.spring310;

import java.util.ArrayList;

public interface InteractionService {
    Interaction createInteraction(Interaction Interaction);

    Interaction getInteraction(int id);
  
    ArrayList<Interaction> getAllInteractions();
  
    Interaction updateInteraction(int id, Interaction Interaction);
  
    void deleteInteraction(int id);

  
}
