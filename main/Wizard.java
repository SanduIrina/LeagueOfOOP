package main;

public class Wizard extends Adam {
	float fProc, sProc, fBaseProc, sBaseProc, fProcUp, sProcUp;
	public Wizard(int CoordX, int CoordY){
		super(CoordX, CoordY, 'W');
		hp = 400;
		originalHP = 400;
		hpUp = 30;
		
		fProc = 0.2f; 
		fBaseProc = 0.2f;
		fProcUp = 0.05f;
		
		sProc = 0.35f; 
		sBaseProc = 0.35f;
		sProcUp = 0.02f;		
	}
	
	public void Abillity1(Adam victim){
		fSkill = Math.round(fProc * Math.min(0.3f * (victim.originalHP + victim.hpUp * victim.level), victim.hp));
		victim.hp -= fSkill;
	}
	
	public void Abillity2(Adam victim){
		sSkill = Math.round(sProc*(victim.fWiz + victim.sWiz));
		victim.hp -= sSkill;
	}
	
	public void LevelUp(){
		level++;
		hp = originalHP + hpUp*level;
		fProc += fProcUp;
		sProc += sProcUp;
		if(sProc > 0.75f)
			sProc = 0.75f;
	}
	
	public void checkXp(){
		while(xp >= 250 + level * 50){
			this.LevelUp();
		}
	}
	
	public void CheckMedium(MapCell cell){
		if(cell.type == 'D'){
			fProc = 1.1f*fProc;
			sProc = 1.1f*sProc;
		}
		fWiz = sWiz = 0;
	}
	
	public void BeChecked(Adam checker){
		checker.CheckOpponent(this);
	}
	
	public void CheckOpponent(Rogue victim){
		fProc = fProc * 0.8f;
		sProc = sProc * 1.2f;
	}
	
	public void CheckOpponent(Knight victim){
		fProc = fProc * 1.2f;
		sProc = sProc * 1.4f;
	}
	
	public void CheckOpponent(Pyromancer victim){
		fProc = fProc * 0.9f;
		sProc = sProc * 1.3f;
	}
	
	public void CheckOpponent(Wizard victim){
		fProc = fProc * 1.05f;
	}
	
	public void restoreDmg(){
		fProc = fBaseProc + fProcUp*level;
		sProc = sBaseProc + sProcUp*level;
	}
}
