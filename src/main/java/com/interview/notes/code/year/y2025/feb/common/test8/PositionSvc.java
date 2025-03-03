package com.interview.notes.code.year.y2025.feb.common.test8;

import java.util.ArrayList;
import java.util.List;

class PositionSvc {
    private List<Position> positions;

    public PositionSvc() {
        this.positions = new ArrayList<>();
        this.positions.add(new Position("286ea600,PEP,25"));
        this.positions.add(new Position("3a8f7d92,VFC,35"));
        this.positions.add(new Position("8f7d928a,NUE,81"));
        this.positions.add(new Position("286ea600,ECL,23"));
        this.positions.add(new Position("3a8f7d92,FRT,30"));
        this.positions.add(new Position("4810b949,DOV,29"));
        this.positions.add(new Position("8f7d928a,FRT,83"));
        this.positions.add(new Position("8f7d928a,SHW,10"));
        this.positions.add(new Position("4810b949,LEG,59"));
        this.positions.add(new Position("b949203a,FRT,80"));
        this.positions.add(new Position("286ea600,WMT,35"));
        this.positions.add(new Position("4810b949,KMB,32"));
        this.positions.add(new Position("4810b949,ECL,11"));
        this.positions.add(new Position("b949203a,KMB,34"));
        this.positions.add(new Position("4810b949,PBCT,469"));
        this.positions.add(new Position("8f7d928a,LEG,71"));
        this.positions.add(new Position("3a8f7d92,WMT,40"));
        this.positions.add(new Position("286ea600,PBCT,223"));
        this.positions.add(new Position("8f7d928a,T,118"));
        this.positions.add(new Position("4810b949,NUE,131"));
        this.positions.add(new Position("286ea600,GD,15"));
        this.positions.add(new Position("286ea600,MCD,22"));
        this.positions.add(new Position("286ea600,FRT,65"));
        this.positions.add(new Position("8f7d928a,DOV,27"));
        this.positions.add(new Position("8f7d928a,HRL,53"));
        this.positions.add(new Position("b949203a,MCD,14"));
        this.positions.add(new Position("3a8f7d92,PBCT,357"));
        this.positions.add(new Position("4810b949,CAH,61"));
        this.positions.add(new Position("8f7d928a,ECL,24"));
        this.positions.add(new Position("b949203a,DOV,38"));
        this.positions.add(new Position("8f7d928a,GD,41"));
        this.positions.add(new Position("3a8f7d92,ABBV,70"));
        this.positions.add(new Position("8f7d928a,PBCT,551"));
        this.positions.add(new Position("3a8f7d92,T,138"));
    }

    public List<Position> getCustomerPositions() {
        return this.positions;
    }
}
