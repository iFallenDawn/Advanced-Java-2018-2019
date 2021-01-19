import java.util.*;

public class BruteForceTree
{
	private Node root;
	private ArrayList<Node> allNodes;
	private static final int base = 5;
	private static int depth = base;
	private int move;
	private double rating;

	public BruteForceTree(int[][][][] state, int player, int active)
	{
		root = new Node(UTTTUtility.deepcopy(state), player, active, 0, 0);
		allNodes = new ArrayList<Node>();
		allNodes.add(root);

		for (int i = 0; i < allNodes.size(); i++)
		{
			Node n = allNodes.get(i);
			if (n.expanded() == false && n.depth < depth)
			{
				n.expand();
			}
			if (n.depth < depth && n.complete == false)
				allNodes.addAll(n.getChildren());
		}
		System.out.println(allNodes.size());
		System.out.println("ANALYSIS DEPTH: " + depth);
		System.out.println("NEW MOVES");

		rating = root.maxVal();
		move = root.getMove();


		Node next = root.getChild(move);
		for (int i = 0; i < depth / 2 - 1; i++)
		{
			if (next.complete == false)
				next = next.getChild(next.getMove());
		}
		ArrayList<Node> nextSet = next.getAll();

		for (int i = 0; i < nextSet.size(); i++)
		{
			Node n = nextSet.get(i);
			n.resetVal();
			if (n.expanded() == false && n.depth < depth + depth / 2)
			{
				n.expand();
			}
			if (n.depth < depth + depth / 2 && n.complete == false)
				nextSet.addAll(n.getChildren());
		}
		rating = root.maxVal();
		move = root.getMove();

		if (allNodes.size() < 100000)
			depth++;
		if (allNodes.size() < 10000)
			depth++;
		if (allNodes.size() < 1000)
			depth++;
	}

	public int bestMove()
	{
		return move;
	}

	public int nextMove()
	{
		for (Node n : allNodes)
		{
			if (n.depth == 1 && n.move == move)
				return n.getMove();
		}
		return 0;
	}

	public ArrayList<Integer> getAllNext()
	{
		for (Node n : allNodes)
		{
			if (n.depth == 1 && n.move == move)
				return n.allNextMoves();
		}
		return new ArrayList<Integer>();

	}

	public ArrayList<Integer> getAll()
	{
		return root.allNextMoves();
	}

	public static void reset()
	{
		depth = base;
	}

	public double getRating()
	{
		return rating;
	}


}