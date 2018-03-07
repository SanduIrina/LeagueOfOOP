package main;

public class Rogue extends Adam{
	float countCrit;
	int someFlag;
	public Rogue(int coordX, int coordY){
		super(coordX, coordY, 'R');
		countCrit = 0;
		hp = 600;
		originalHP = 600;
		hpUp = 40;
		fSkill = 200;
		fSkillBase = 200;
		fSkillUp = 20;
		sSkill = 40;
		sSkillBase = 40;
		sSkillUp = 10;
	}
	public void Abillity1(Adam victim){
		victim.hp -= Math.round(fSkill);
	}
	
	public void Abillity2(Adam victim){
		victim.dotRounds = someFlag;
		victim.rootFlag = someFlag;	
		victim.dotDmg = sSkill;
		victim.hp -= Math.round(sSkill);
	}
	
	public void LevelUp(){
		level++;
		hp = originalHP + hpUp*level;
		fSkill += fSkillUp;
		sSkill += sSkillUp;
	}
	
	public void checkXp(){
		while(xp >= 250 + level * 50)
			this.LevelUp();
	}
	
	public void CheckMedium(MapCell cell){
		if(cell.type == 'W'){
			someFlag = 6;	
			fSkill = Math.round(1.15f*fSkill);
			sSkill = Math.round(1.15f*sSkill);
			fWiz = fSkill;
			sWiz = sSkill;
			if(countCrit%3 == 0){
				fSkill = Math.round(1.5f*fSkill);
				fWiz = fSkill;
			}
		}
		else {
			someFlag = 3;
			fWiz = fSkillBase;
			sWiz = sSkillBase;
		}
		countCrit++;
	}
	
	public void BeChecked(Adam checker){
		checker.CheckOpponent(this);
	}
	
	public void CheckOpponent(Rogue victim){
		fSkill += Math.round(fSkill/5);
		sSkill -= Math.round(sSkill/10);
	}
	
	public void CheckOpponent(Knight victim){
		fSkill -= fSkill/10;
		sSkill -= sSkill/5;
	}
		
	public void CheckOpponent(Pyromancer victim){
		fSkill += Math.round(fSkill/4);
		sSkill += Math.round(sSkill/5);
	}
		
	public void CheckOpponent(Wizard victim){
		fSkill += Math.round(fSkill/4);
		sSkill += Math.round(sSkill/4);
	}
	
	public void restoreDmg(){
		fSkill = fSkillBase + fSkillUp*level;
		sSkill = sSkillBase + sSkillUp*level;
	}
}
