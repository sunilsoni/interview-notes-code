package com.interview.notes.code.year.y2025.jan.test10;

import java.util.ArrayList;
import java.util.List;

class PositionSvc {
    private final List<Position> positions;

    public PositionSvc() {
        this.positions = new ArrayList<>();
        initializePositions();
    }

    private void initializePositions() {
        positions.add(new Position("4810b949,SHW,10"));
        positions.add(new Position("286ea600,LEG,75"));
        positions.add(new Position("286ea600,SHW,7"));
        positions.add(new Position("3a8f7d92,CAH,52"));
        positions.add(new Position("3a8f7d92,OTIS,110"));
        positions.add(new Position("286ea600,VFC,84"));
        positions.add(new Position("3a8f7d92,PEP,26"));
        positions.add(new Position("3a8f7d92,GD,44"));
        positions.add(new Position("3a8f7d92,SHW,4"));
        positions.add(new Position("b949203a,BDX,28"));
        positions.add(new Position("b949203a,VFC,55"));
        positions.add(new Position("3a8f7d92,MCD,23"));
        positions.add(new Position("8f7d928a,VFC,77"));
        positions.add(new Position("8f7d928a,KMB,31"));
        positions.add(new Position("8f7d928a,MCD,10"));
        positions.add(new Position("b949203a,SHW,7"));
        positions.add(new Position("b949203a,WMT,48"));
        positions.add(new Position("8f7d928a,BDX,13"));
        positions.add(new Position("b949203a,ROP,13"));
        positions.add(new Position("b949203a,LEG,105"));
        positions.add(new Position("286ea600,PEP,25"));
        positions.add(new Position("3a8f7d92,VFC,35"));
        positions.add(new Position("8f7d928a,NUE,81"));
        positions.add(new Position("286ea600,ECL,23"));
        positions.add(new Position("3a8f7d92,FRT,30"));
        positions.add(new Position("4810b949,DOV,29"));
        positions.add(new Position("8f7d928a,FRT,83"));
        positions.add(new Position("8f7d928a,SHW,10"));
        positions.add(new Position("4810b949,LEG,59"));
        positions.add(new Position("b949203a,FRT,80"));
        positions.add(new Position("286ea600,WMT,35"));
        positions.add(new Position("4810b949,KMB,32"));
        positions.add(new Position("4810b949,ECL,11"));
        positions.add(new Position("b949203a,KMB,34"));
        positions.add(new Position("4810b949,PBCT,469"));
        positions.add(new Position("8f7d928a,LEG,71"));
    }

    public List<Position> getCustomerPositions() {
        return positions;
    }
}

