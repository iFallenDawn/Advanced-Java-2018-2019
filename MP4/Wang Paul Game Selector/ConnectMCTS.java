import java.util.*;
import java.math.*;
import java.io.*;

public class ConnectMCTS
{
	private int[][] board;
	private int player;
	private int bestMove;

	public static void write_file(int num_iterations)
	{
		try {
			for (int j = 31; j < 101; j++)
			{
				File file = new File("good_data/connect_" + j + "_" + num_iterations + ".json");
				file.createNewFile();
				ArrayList<int[][]> xData = new ArrayList<int[][]>();
				ArrayList<Integer> yData = new ArrayList<Integer>();
				for (int i = 0; i < num_iterations; i++)
				{
					int[][] board = new int[6][7];
					ArrayList<int[][]> usefulBoard = randomBoard(6, 7);
					for (int k = 0; k < board.length; k++)
					{
						for (int c = 0; c < board[k].length; c++)
							board[k][c] = usefulBoard.get(0)[k][c];
					}
					xData.add(board);
					ConnectMCTS mcts = new ConnectMCTS(board, 3 - usefulBoard.get(1)[0][0], Math.sqrt(2), 10000);
					int move = mcts.bestMove();
					yData.add(move);
				//	printBoard(board);
				//	System.out.println();
				//	printBoard(usefulBoard);
				//	System.out.println("\n");
					if (i % 10 == 0)
						System.out.println(i / 10);
				}
				printData(file, xData, yData);
			}
		}
		catch (Exception ex) {}
	}

	public static void printData(File f, ArrayList<int[][]> xData, ArrayList<Integer> yData)
	{
		try {
		FileWriter out = new FileWriter(f);
		//Print all X:
		out.write("[[");
		for (int i = 0; i < xData.size(); i++)
		{
			out.write("[");
			int[][] b = xData.get(i);
	//		for (int turn = 1; turn < 3; turn++)
	//		{
	//			out.write("[");
				for (int r = 0; r < b.length; r++)
				{
					out.write("[");
					for (int c = 0; c < b[r].length; c++)
					{
						out.write("[");
						if (b[r][c] == 1)
							out.write("0, 1");
						else if (b[r][c] == 2)
							out.write("1, 0");
						else
							out.write("0, 0");
						out.write("]");
						if (c < b[r].length - 1)
							out.write(", ");
					}
					out.write("]");
					if (r < b.length - 1)
						out.write(", ");
				}
		//		out.write("]");
		//		if (turn == 1)
		//			out.write(", ");
		//	}
			out.write("]");

			if (i < xData.size() - 1)
				out.write(", ");
		}
		out.write("], ");
		//Print all Y
		out.write("[");
		for (int i = 0; i < yData.size() - 1; i++)
			out.write(Arrays.toString(oneHot(yData.get(i), 7)) + ", ");
		out.write(Arrays.toString(oneHot(yData.get(yData.size() - 1), 7)));
		out.write("]]");
		out.close();
		} catch (Exception ex) {}
	}

	public static int[] oneHot(int val, int numVals)
	{
		int[] arr = new int[numVals];
		arr[val] = 1;
		return arr;
	}

	public static ArrayList<int[][]> randomBoard(int x, int y)
	{
		int[][] board = new int[x][y];
		int numMoves = 2 * ((int) ((Math.random() * (x * y + 1) / 2 ))) + 1;
		ConnectMCTS mcts = new ConnectMCTS(board, 2, 0, 1);
		int play = 1;
		for (int i = 0; i < numMoves; i++)
		{
			ArrayList<Integer> possible = mcts.possibleMoves(board);
			int move = possible.get((int) (Math.random() * possible.size()));
		//	System.out.print(" " + numMoves + " ");
		//	System.out.print(move);
			int win = mcts.makeMove(board, move, play);
			play = 3 - play;
			if (win != 0)
				return randomBoard(x, y);
		}
	//	board[x][0] = play;
	//	System.out.println(numMoves);
		ArrayList<int[][]> list = new ArrayList<int[][]>();
		list.add(board);
		int[][] p = new int[1][1];
		p[0][0] = play;
		list.add(p);
		return list;
	}

	public static void test(double exp1, double exp2, int trials)
	{
		int[] wins = new int[3];
		for (int i = 0; i < trials; i++)
		{
			int[][] board = new int[6][7];
			ConnectMCTS player = new ConnectMCTS(board, 2, exp1, 100000);
			int count = 0;
			int win = player.win(board);
			while (win == 0 && player.possibleMoves(board).size() > 0)
			{
				int play = 1 + (count % 2);
				double exp = exp1;
				if (count % 2 == 1)
					exp = exp2;
				if (count % 2 == 0)
					player = new ConnectMCTS(board, 3 - play, exp, 10000);
				else
					player = new ConnectMCTS(board, 3 - play, exp, 50000);
				int move = player.bestMove();
				win = player.makeMove(board, move, play);
				count++;
			}
			System.out.print(win);
			if (win != 0)
				wins[win - 1]++;
			else
				wins[2]++;
		}
		System.out.println("\nEXP1 WINS AS P1: " + wins[0]);
		System.out.println("EXP1 WINS AS P2: " + wins[1]);
		System.out.println("TIES: " + wins[2]);
	}

	public ConnectMCTS(int[][] board, int player, double exploration, int iterations)
	{
		this.board = board;
		this.player = player;
		GameTree tree = new GameTree(board, player, exploration);
		for (int i = 0; i < iterations; i++)
		{
			tree.simulate();
		}
		int total = 0;
		if (tree.getRoot().getComplete() == false)
		{
			ArrayList<Node> children = tree.getRoot().getChildren();

			Node nextMove = children.get(0);
			double maxVal = nextMove.getWins();
			for (Node child : tree.getRoot().getChildren())
			{
				double val = child.getValue();
				total += val;
			//	System.out.println(child.getMove() + "\t" + val + "\t" + child.weight() + "\t" + child.getWin() + "\t" + child.getWins() + "\t" + child.getVisits() + "\t" + child.getPlayer());
				if (val > maxVal)
				{
					nextMove = child;
					maxVal = val;
				}
			}
			bestMove = nextMove.getMove();
		}
	//	System.out.println(total);
	}

	public int bestMove()
	{
		return bestMove;
	}

	public int[][] withMove(int[][] board, int move, int player)
	{
		int[][] grid = deepcopy(board);
		makeMove(grid, move, player);
		return grid;
	}

	public int[][] deepcopy(int[][] board)
	{
		int[][] grid = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				grid[i][j] = board[i][j];
			}
		}
		return grid;
	}

	public int makeMove(int[][] board, int move, int player)
	{

		int i = board.length - 1;
		while (i >= 0 && board[i][move] != 0)
			i--;
	//	System.out.print(move);
	//	System.out.print(i);
		board[i][move] = player;
		if (win(board, player))
			return player;
		return 0;
	}

	public double randomGame(int[][] board, int player)
	{
		int[][] grid = deepcopy(board);
		int win = 0;
		while (win == 0)
		{
			ArrayList<Integer> moves = possibleMoves(grid);
			if (moves.size() == 0)
			{
				return 2.5;
			}
			int x = (int) (moves.size() * Math.random());
			int move = moves.get(x);
			win = makeMove(grid, move, player);
			player = 3 - player;
		}
		return win;
	}

	public ArrayList<Integer> possibleMoves(int[][] board)
	{
		ArrayList<Integer> possible = new ArrayList<Integer>();
		for (int i = 0; i < board[0].length; i++)
		{
			if (board[0][i] == 0)
			{
				possible.add(i);
			}
		}
		return possible;
	}


	public int win(int[][] board)
	{
		if (win(board, 1))
			return 1;
		if (win(board, 2))
			return 2;
		return 0;
	}
	private class Node
	{
		private GameTree tree;
		private int[][] state;
		private Node parent;
		private Node penult;
		private ArrayList<Node> children, winningMoves;
		private int visits = 0;
		private double wins = 0;
		private int depth = 0;
		private boolean complete = false;
		private int player;
		private boolean expanded = false;
		private double win = 0;
		int move = -1;

		public Node(int[][] state, GameTree tree, Node parent)
		{
			this.parent = parent;
			this.state = state;
			if (parent == null)
				depth = 0;
			else
				depth = parent.getDepth() + 1;
			children = new ArrayList<Node>();
			if (depth == 0)
			{
				penult = null;
				player = tree.getPlayer();
			}
			else if (depth == 1)
			{
				penult = this;
				if (this.parent.getPlayer() == 1)
					player = 2;
				else
					player = 1;
			}
			else
			{
				penult = parent.penult;
				if (this.parent.getPlayer() == 1)
					player = 2;
				else
					player = 1;
			}

			this.tree = tree;

			this.win = win(this.state);
			if (win != 0)
			{
				complete = true;
			}
			if (possibleMoves(this.state).size() == 0)
			{
				complete = true;
				win = 1.5;
			}
		}

		public Node(int[][] state, GameTree tree, Node parent, int move)
		{
			this(state, tree, parent);
			this.move = move;
		}

		public double weight()
		{
			double useful = tree.getExploration();
			double weight = 1.0 * wins / (visits + 1);
			double val = Math.log(parent.getValue() + 1);
			val /= (visits + 1);
			val = Math.sqrt(val);
			weight += useful * val;
			weight *= weight;
			return weight;
		}

		public int getPlayer() { return player; }

		public int getDepth() { return depth; }

		public boolean getExpanded() { return expanded; }

		public boolean getComplete() { return complete; }

		public ArrayList<Node> getChildren() { return children; }

		public double getValue() { return visits; }

		public int getMove() { return move; }

		public double getWin() { return win; }

		public double getWins() { return wins; }

		public int getVisits() { return visits; }

		public void expand()
		{
			if (!(expanded || complete))
			{
				expanded = true;

				ArrayList<Integer> possible = possibleMoves(state);
				for (int p : possible)
				{
					int[][] nextState = withMove(state, p, 3 - this.player);
					Node next = new Node(nextState, this.tree, this, p);
					children.add(next);
					this.tree.getNodes().add(next);
					if (next.getWin() != 0)
					{
			//			System.out.println("Found a winning move!");
						if (winningMoves == null)
							winningMoves = new ArrayList<Node>();
						winningMoves.add(next);
					}
				}
			}
		}

		public void updateBack(double win)
		{
			if (player == win)
			{
				wins++;
			}
			if (win == 1.5)
			{
				wins += 0.5;
			}
			visits += 1;
			if (parent != null)
				parent.updateBack(win);
		}

		public void updateBack()
		{
			if (player == win)
			{
				wins++;
			}
			if (win == 1.5)
			{
				wins += 0.5;
			}
			visits += 1;
			if (parent != null)
				parent.updateBack(this.win);
		}

		public Node randomChild()
		{
			if (!expanded)
				this.expand();
			if (winningMoves != null)
			{
			//	System.out.println("Found a winning move!");
				int index = (int) (winningMoves.size() * Math.random());
				return winningMoves.get(index);
			}
			double total = 0;
			for (Node child : children)
				total += child.weight();
			double choice = total * Math.random();
			if (choice == 0)
			{
				int index = (int) (children.size() * Math.random());
				return children.get(index);
			}
			int count = 0;
			Node child = null;
		//	System.out.println(choice);
			while (choice > 0)
			{
		//		System.out.println(count + "\t" + choice);
				child = children.get(count);
				choice -= child.weight();
				count++;
			}
			return child;

		}

		public void rollout()
		{
			double winner = randomGame(this.state, this.player);
	//		System.out.println(winner);
			updateBack(winner);
		}
	}

	private class GameTree
	{
		private ArrayList<Node> nodes;
		private Node root;
		private int player;
		private double exploration;

		public GameTree(int[][] initial, int player, double exploration)
		{
			nodes = new ArrayList<Node>();
			this.player = player;
			this.exploration = exploration;
			root = new Node(initial, this, null);
			nodes.add(root);
		}


		public int getPlayer() { return player; }

		public Node getRoot() { return root; }

		public ArrayList<Node> getNodes() { return nodes; }

		public double getExploration() { return exploration; }

		public void simulate()
		{
			Node no = root;
			while (!no.getComplete() && no.getExpanded()) { no = no.randomChild(); }
			if (no.getComplete())
			{
				no.updateBack();
			}
			else if (!no.getExpanded())
			{
				no.expand();
				no = no.randomChild();
				no.rollout();
			}
		}
	}

	public boolean win(int[][] grid, int player)
		{
			//System.out.print(player);
			int rows = grid.length;
			int cols = grid[0].length;
			int count = 0;
			//Checking horizontals
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
				{
					if(grid[i][j] == player)
						count++;
					else
					{
						count = 0;
					}
					if(count == 4)
					{
				//		System.out.println("Horizontal");
						return true;
					}
				}
				count = 0;
			}
			//Checking verticals
			for(int i = 0; i < cols; i++)
			{
				for(int j = 0; j < rows; j++)
				{
					if(grid[j][i] == player)
					{
						count++;
						//System.out.println("i: " + i + "\tj:" + j+ " IF " + count);
					}
					else
					{
						count = 0;
						//System.out.println("i: " + i + "\tj:" + j+ " else " + count);
					}
					if(count == 4)
					{
			//			System.out.println("Vertical");
						return true;
					}
				}
				count = 0;
			}
			//Checking diagonals from top left to bottom right by checking bounds first before checking location
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
				{
					if(grid[i][j] == player)
					{
						if(i + 1 < rows && j + 1 < cols && grid[i+1][j+1] == player)
						{
							if(i + 2 < rows && j + 2 < cols && grid[i+2][j+2] == player)
							{
								if(i + 3 < rows && j + 3 < cols && grid[i+3][j+3] == player)
								{
						//			System.out.println("Top left diagonal");
									return true;
								}
							}
						}
					}
				}
				count = 0;
			}
			//Checking diagonals from top right to bottom left by checking bounds first before checking location
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
				{
					if(grid[i][j] == player)
					{
						if(i + 1 < rows && j - 1 > -1 && grid[i+1][j-1] == player)
						{
							if(i + 2 < rows && j - 2 > -1 && grid[i+2][j-2] == player)
							{
								if(i + 3 < rows && j - 3 > -1 && grid[i+3][j-3] == player)
								{
					//				System.out.println("Top right diagonal");
									return true;
								}
							}
						}
					}
				}
				count = 0;
			}
			return false;
	}

	public static void printBoard(int[][] grid)
	{
		String result = "";
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				System.out.print(grid[i][j] + "\t");
			}
			System.out.println();
		}
//		return result;

	}
}