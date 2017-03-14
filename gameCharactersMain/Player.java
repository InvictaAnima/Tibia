package gameCharactersMain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import items.BrocadeBackpack;
import items.LeatherBoots;
import items.LeatherLegs;
import items.MageHat;
import items.MagicRobe;
import items.SnakebiteRod;
import items.Spellbook;
import itemsMain.Equipment;
import tibia.Map;
import tibia.MapSuper;

public class Player extends GameCharacter {

	protected int rowOnScreen, columnOnScreen;
	protected Map mapReference;
	protected Timer timerMoveMap;

	protected int healthPerLevel;

	protected int manaPerLevel;
	protected int maxManaPoints;
	protected int manaPoints;
	protected int manaPointsRegeneration; // how mych hp per 2s

	protected int level;
	protected int experience;
	protected int[] experienceTable;
	protected int magicLevel;
	protected int[] manaPerMagicLevel;
	protected int usedMana; // at current mlvl
	protected int attack;
	protected int shielding;

	protected Equipment eq;

	public Player(Map map, int row, int column) {
		super(row, column);
		this.rowOnScreen = row;
		this.columnOnScreen = column;
		mapReference = map;
		movementSpeed = 10;

		level = 8;
		experience = 4200;
		setExperienceTable();

		magicLevel = 8;
		setManaPerMagicLevel();

		eq = new Equipment();
		setBasicEq();

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				regenerateMana();
			}
		}, 0, 2000);
	}

	@Override
	public void draw(Graphics g) {

	}

	public void playerDrawHpBar(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(columnOnScreen * 32 + tmpX - 16, rowOnScreen * 32 + tmpY - 22, 32, 4);
		g.setColor(Color.green);
		g.fillRect(columnOnScreen * 32 + tmpX - 16, rowOnScreen * 32 + tmpY - 22,
				(int) (32 * ((float) healthPoints / (float) maxHealthPoints)), 4);
		g.setColor(Color.black);
		g.drawRect(columnOnScreen * 32 + tmpX - 16, rowOnScreen * 32 + tmpY - 22, 32, 4);
	}

	public void playerDrawName(Graphics g) {
		g.setColor(Color.green);
		g.drawString(name, columnOnScreen * 32 + tmpX - (name.length() * 3), rowOnScreen * 32 + tmpY - 24);
	}	

	@Override
	public void update() {

	}

	protected void regenerateMana() {
		manaPoints += manaPointsRegeneration;
		if (manaPoints > maxManaPoints) {
			manaPoints = maxManaPoints;
		}
	}

	protected void setExperienceTable() {
		experienceTable = new int[] { 0, 100, 200, 400, 800, 1500, 2600, 4200, 6400, 9300, 13000, 17600, 23200, 29900,
				37800, 47000, 57600, 69700, 83400, 98800 };
	}

	protected void setManaPerMagicLevel() {

	}

	protected void setBasicEq() {
		eq.equip(new LeatherLegs());
		eq.equip(new MageHat());
		eq.equip(new MagicRobe());
		eq.equip(new LeatherBoots());
		eq.equip(new SnakebiteRod());
		eq.equip(new Spellbook());
		eq.equip(new BrocadeBackpack());
		
	}

	@Override
	public void move() {
		if (destRow < tmpRow) {
			direction = 0;
		} else if (destRow > tmpRow) {
			direction = 3;
		} else if (destColumn > tmpColumn) {
			direction = 2;
		} else {
			direction = 1;
		}

		if (destRow < tmpRow) {
			tmpY--;
		} else if (destRow > tmpRow) {
			tmpY++;
		}

		if (destColumn < tmpColumn) {
			tmpX--;
		} else if (destColumn > tmpColumn) {
			tmpX++;
		}

		if ((tmpX % 2 == 0) && (tmpY % 2 == 0)) {
			animationFrame++;
			animationFrame = animationFrame % 8;
		}

		if (tmpY >= 16 || tmpX >= 16 || tmpY <= -16 || tmpX <= -16) {
			row = destRow;
			column = destColumn;
		}

		if (tmpY >= 32 || tmpX >= 32 || tmpY <= -32 || tmpX <= -32) {
			if (destRow < tmpRow) {
				rowOnScreen--;
			} else if (destRow > tmpRow) {
				rowOnScreen++;
			} else if (destColumn < tmpColumn) {
				columnOnScreen--;
			} else if (destColumn > tmpColumn) {
				columnOnScreen++;
			}

			tmpRow = destRow;
			tmpColumn = destColumn;
			tmpX = tmpY = 0;
			timerMove.cancel();
			isMoving = false;
		}
	}

	public void setTimerMoveMap() {
		if (isMoving == false) {
			isMoving = true;
			setMapDirection();
			timerMoveMap = new Timer();
			timerMoveMap.schedule(new TimerTask() {
				@Override
				public void run() {
					moveMap();
				}
			}, 0, 10);// 60

			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					moveMapChangePosition();
				}
			}, 100);

		}
	}

	private void setMapDirection() {
		if (Map.getInitColumn() < Map.getNewInitColumn()) {
			direction = 2;
		} else if (Map.getInitColumn() > Map.getNewInitColumn()) {
			direction = 1;
		} else if (Map.getInitRow() < Map.getNewInitRow()) {
			direction = 3;
		} else if (Map.getInitRow() > Map.getNewInitRow()) {
			direction = 0;
		}
	}

	public void moveMap() {
		if ((Map.getTmpX() % 2 == 0) && (Map.getTmpY() % 2 == 0)) {
			animationFrame++;
			animationFrame = animationFrame % 8;
		}

		if (((Map.getNewInitRow() * 32) == (Map.getInitRow() * 32 + Map.getTmpY()))
				&& ((Map.getNewInitColumn() * 32) == (Map.getInitColumn() * 32 + Map.getTmpX()))) {
			timerMoveMap.cancel();
			animationFrame = 0;
			isMoving = false;
		}
	}

	public void moveMapChangePosition() {
		if (MapSuper.getNewInitRow() < MapSuper.getInitRow()) {
			row--;
			destRow--;
		} else if (MapSuper.getNewInitRow() > MapSuper.getInitRow()) {
			row++;
			destRow++;
		} else if (MapSuper.getNewInitColumn() < MapSuper.getInitColumn()) {
			column--;
			destColumn--;
		} else if (MapSuper.getNewInitColumn() > MapSuper.getInitColumn()) {
			column++;
			destColumn++;
		}
	}

	public int getMaxManaPoints() {
		return maxManaPoints;
	}

	public int getManaPoints() {
		return manaPoints;
	}

	public void setManaPoints(int manaPoints) {
		this.manaPoints = manaPoints;
	}

	public Map getMapReference() {
		return mapReference;
	}

	public int getRowOnScreen() {
		return rowOnScreen;
	}

	public int getColumnOnScreen() {
		return columnOnScreen;
	}

	public int getLevel() {
		return level;
	}

	public int getMagicLevel() {
		return magicLevel;
	}

	public void setExperience(int experience) {
		this.experience = experience;
		if (experience >= experienceTable[level]) {
			level++;
			maxHealthPoints += healthPerLevel;
			maxManaPoints += manaPerLevel;
		}
	}

	public int getExperience() {
		return experience;
	}

	public int getExperienceTableElement(int i) {
		return experienceTable[i];
	}

	public int getManaPerMagicLevelTableElement(int i) {
		return manaPerMagicLevel[i];
	}

	public int getUsedMana() {
		return usedMana;
	}

	public void setUsedMana(int usedMana) {
		this.usedMana = usedMana;
		if (usedMana >= manaPerMagicLevel[magicLevel]) {
			magicLevel++;
			usedMana = 0;
		}
	}

	public Equipment getEq() {
		return eq;
	}

}
