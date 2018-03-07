package main;

public class Pyromancer extends Adam{
	int pyroDot, pyroDotUp;
	public Pyromancer(int coordX, int coordY){
		super(coordX, coordY, 'P');
		hp = 500;
		originalHP = 500;
		hpUp = 50;
		
		fSkill = 350;
		fSkillBase = 350;
		fSkillUp = 50;
		
		sSkill = 150;
		sSkillBase = 150;
		sSkillUp = 20;
		
		pyroDot = 50;
		pyroDotUp = 30;
	}
	
	public void Abillity1(Adam victim){
		victim.hp -= Math.round(fSkill);
	}
	
	public void Abillity2(Adam victim){
		victim.hp -= Math.round(sSkill);
		victim.dotDmg = pyroDot;
		victim.dotRounds = 2;
		victim.rootFlag = 0;
	}
	
	public void LevelUp(){
		level++;
		hp = originalHP + hpUp*level;
		fSkill += fSkillUp;
		sSkill += sSkillUp;
		pyroDot += pyroDotUp;
	}
	
	public void checkXp(){
		while(xp >= (250 + level * 50))
			LevelUp();
	}
	
	public void CheckMedium(MapCell cell){
		if(cell.type == 'V'){
			fSkill = Math.round(1.25f*fSkill);
			sSkill = Math.round(1.25f*sSkill);
			pyroDot += Math.round(pyroDot/4);
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
		fSkill -= Math.round(fSkill/5);
		sSkill -= Math.round(sSkill/5);
		pyroDot -= Math.round(pyroDot/5);
	}
		
	public void CheckOpponent(Knight victim){
		fSkill += Math.round(fSkill/5);
		sSkill += Math.round(sSkill/5);
		pyroDot += Math.round(pyroDot/5);
	}
		
	public void CheckOpponent(Pyromancer victim){
		fSkill -= Math.round(fSkill/10);
		sSkill -= Math.round(sSkill/10);
		pyroDot -= Math.round(pyroDot/10);
	}
		
	public void CheckOpponent(Wizard victim){
		fSkill += Math.round(fSkill/20);
		sSkill += Math.round(sSkill/20);
		pyroDot += Math.round(pyroDot/20);
	}
	
	public void restoreDmg(){
		fSkill = fSkillBase + fSkillUp*level;
		sSkill = sSkillBase + sSkillUp*level;
	}
}
