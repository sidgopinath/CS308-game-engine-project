package player.level;

public interface LevelInfo {
	public double getMoney();
	public double getScore();
	public double getHealth();
	public void updateDroppable(String id);
}
