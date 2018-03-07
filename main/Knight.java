package main;

public class Knight extends Adam {
	float hpLimit;
	public Knight(int coordX, int coordY){
		super(coordX, coordY, 'K');
		originalHP = 900;
		hp = 900;
		hpUp = 80;
		fSkill = 200;
		fSkillBase = 200;
		fSkillUp = 30;
		sSkill = 100;
		sSkillBase = 100;
		sSkillUp = 40;
	}
	
	public void Abillity1(Adam victim){
		if(0.2f + 0.01f * level > 0.4f)
			hpLimit = 0.4f * (victim.originalHP + victim.hpUp * victim.level) ;
		else 
			hpLimit = (0.2f + 0.01f* level) * (victim.originalHP + victim.hpUp * victim.level);
		if(victim.hp <= hpLimit)
			victim.hp = 0;
		else
			victim.hp -= Math.round(fSkill);
	}
	
	public void Abillity2(Adam victim){
		victim.hp -= Math.round(sSkill);
		victim.rootFlag = 1;
		victim.dotDmg = 0;
		victim.dotRounds = 0;
	}
	
	public void LevelUp(){
		level++;
		hp = originalHP + hpUp*level;
		fSkill = Math.round(fSkill + fSkillUp);
		sSkill = Math.round(sSkill + sSkillUp);
	}
	
	public void checkXp(){
		while(xp >= 250 + level * 50)
			this.LevelUp();
	}
	
	public void CheckMedium(MapCell cell){
		if(cell.type == 'L'){
			fSkill = Math.round(1.15f * fSkill);
			sSkill = Math.round(1.15f * sSkill);
			fWiz = fSkill;
			sWiz = sSkill;
		}
		else{
			fWiz = fSkillBase;
			sWiz = sSkillBase;
		}
	}
	
	public void BeChecked(Adam checker){
		checker.CheckOpponent(this);
	}
	
	public void CheckOpponent(Rogue victim){
		fSkill += Math.round(fSkill*3/20);
		sSkill -= Math.round(sSkill/5);
	}
	
	public void CheckOpponent(Knight victim){
		sSkill += Math.round(sSkill/5);
	}
		
	public void CheckOpponent(Pyromancer victim){
		fSkill += Math.round(fSkill/10);
		sSkill -= Math.round(sSkill/10);
	}
		
	public void CheckOpponent(Wizard victim){
		fSkill -= Math.round(fSkill/5);
		sSkill += Math.round(sSkill/20);
	}
	
	public void restoreDmg(){
		fSkill = fSkillBase + fSkillUp*level;
		sSkill = sSkillBase + sSkillUp*level;
	}
	
}
