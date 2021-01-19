import java.util.*;

public class BruteForceTree
{
	private Node root;
	private ArrayList<Node> allNodes;
	private static final int base = 5;
	private static int depth = base;
	private static final int scale = 50;
	private int move;
	private double rating;

	public BruteForceTree(int[][][][] state, int player, int active)
	{
		root = new Node(UTTTUtility.deepcopy(state), player, active, 0, 0);
	}

	public void compute()
	{
		allNodes = new ArrayList<Node>();
		allNodes.add(root);

		for (int i = 0; i < allNodes.size(); i++)
		{
			Node n = allNodes.get(i);
			if (n.expanded() == false && n.depth < depth)
			{
				n.expand();
			}
			if ((n.depth < depth || n.expanded) && n.complete == false)
				allNodes.addAll(n.getChildren());
		}
	//	System.out.println(allNodes.size());
	//	System.out.println("ANALYSIS DEPTH: " + depth);
		Node next = root;
		for (int j = 0; j < scale; j++)
		{
			for (Node n : allNodes)
				n.resetVal();
			rating = root.maxVal();
			move = root.getMove();
			next = root;
			if (next.complete == false)
			{
				next = next.getChild(move);
				while (next.depth < next.maxDepth() - depth / 2)
				{
					if (next.complete == false)
						next = next.getChild(next.getMove());
					next.resetVal();
				}
				int maxDepth = next.maxDepth();
				ArrayList<Node> nextSet = next.getAll();
				for (int i = 0; i < nextSet.size(); i++)
				{
					Node n = nextSet.get(i);
					n.resetVal();
					if (n.expanded() == false && n.depth < maxDepth + depth / 2)
					{
						n.expand();
					}
					if (n.depth < maxDepth + depth / 2 && n.complete == false)
						nextSet.addAll(n.getChildren());
				}
				allNodes.addAll(nextSet);
			}
		}
		for (Node n : allNodes)
			n.resetVal();
		rating = root.maxVal();
		move = root.getMove();

		if (allNodes.size() < 100000)
			depth++;
		if (allNodes.size() < 10000)
			depth++;
		if (allNodes.size() < 1000)
			depth++;
//		System.out.println(allNodes.size());
//		System.out.println(rating);

	}

	public int bestMove()
	{
		compute();
		return move;
	}

	public int move()
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

	public void makeMove(int move)
	{
		root.reduceDepth();
		root = root.getChild(move);
	/*	for (Node n : root.getChildren())
		{
			if (n.move != move)
			{
				ArrayList<Node> all = n.getAll();
				System.out.println("REMOVING ALL");
				for (int i = 0; i < scale; i++)
				{

					System.out.print("THING");
					allNodes.removeAll(all);
				}
			}
		}*/
	}

	public int min()
	{
		return root.getChild(move).minDepth();
	}

	public int max()
	{
		return root.getChild(move).maxDepth();
	}


}