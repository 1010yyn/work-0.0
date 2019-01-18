#include<iostream>
#include<ctime>
#include<cstdlib>
using namespace std;
const int m = 4;
class game
{
public:
	int i, j;
	game() {
		count1 = 0;
		for (i = 0; i < m; i++)
			for (j = 0; j < m; j++)
				chessboard[i][j] = 0;
		srand((unsigned)time(NULL));
		x = rand() % m;
		y = rand() % m;
		if (count1 == 1 || count1 == 0)
			chessboard[x][y] = 2;
		else
			chessboard[x][y] = 4;
		showchessboard();
	}//构造初始棋盘
	void add(int count1);//新增数字
	void showchessboard();//显示棋盘
	void up();//上移
	void down();//下移
	void left();//左移
	void right();//右移
	bool gameover();//游戏失败
private:
	int chessboard[m][m];
	int x, y, count1, count2, temp1, temp2, k;//c1-连消，c2-空位标记，t1-判连消，t2,k-临时变量
	bool flag;//判消
};
void game::add(int count1)
{
	for (i = 0; i < m; i++)
		for (j = 0; j < m; j++)
		{
			if (chessboard[i][j] == 0)
				goto loop;
		}
	showchessboard();
	return;
loop:srand((unsigned)time(NULL));
	do {
		x = rand() % m;
		y = rand() % m;
	} while (chessboard[x][y] != 0);
	if (count1 < 2)
		chessboard[x][y] = 2;
	else
		chessboard[x][y] = 4;
	showchessboard();
}
void game::showchessboard()
{
	printf("———————————\n");
	for (i = 0; i < m; i++)
	{
		printf("|");
		for (j = 0; j < m; j++)
		{
			if (chessboard[i][j] == 0)
				printf("____|");
			else
			{
				if (chessboard[i][j] >999)
					printf("%d|", chessboard[i][j]);
				else if (chessboard[i][j]>99 && chessboard[i][j] < 999)
					printf(" %d|", chessboard[i][j]);
				else if (chessboard[i][j] < 99 && chessboard[i][j]>9)
					printf(" %d |", chessboard[i][j]);
				else if (chessboard[i][j] < 9)
					printf("  %d |", chessboard[i][j]);
			}
		}
		printf("\n");
	}
	printf("———————————\n");
	printf("Tips:\n1.W——上移\n2.S——下移\n3.A——左移\n4.D——右移\n5.E——退出\n请输入指令：\n");
}
void game::up()
{
	temp1 = count1;
	flag = false;
	for (j = 0; j < m; j++)
		for (i = 0; i < m;)
		{
			for (; i < 4 && chessboard[i][j] == 0; i++); // 找非零值
			if (i == 4)
				break;
			else
			{
				for (k = i + 1; k < 4 && chessboard[k][j] == 0; k++);//找下一个非零值
				if (k == 4)
					break;
				else if (chessboard[i][j] == chessboard[k][j])//匹配
				{
					chessboard[i][j] *= 2;
					chessboard[k][j] = 0;
					i = k + 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[k][j] && k < 4)//不匹配
				{
					i = k;
				}
			}
		}
	for (j = 0; j < m; j++)//排列棋盘
		for (i = 0, count2 = 0; i < m; i++)
		{
			if (chessboard[i][j] != 0)
			{
				temp2 = chessboard[i][j];
				chessboard[i][j] = 0;
				chessboard[count2][j] = temp2;
				count2++;
			}
		}
}
void game::down()
{
	temp1 = count1;
	flag = false;
	for (j = 0; j < m; j++)
		for (i = m - 1; i >= 0;)
		{
			for (; i >= 0 && chessboard[i][j] == 0; i--); // 找非零值
			if (i == -1)
				break;
			else
			{
				for (k = i - 1; k >= 0 && chessboard[k][j] == 0; k--);//找下一个非零值
				if (k == -1)
					break;
				else if (chessboard[i][j] == chessboard[k][j])//匹配
				{
					chessboard[i][j] *= 2;
					chessboard[k][j] = 0;
					i = k - 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[k][j])//不匹配
				{
					i = k;
				}
			}
		}
	for (j = 0; j < m; j++)
		for (i = m - 1, count2 = m - 1; i >= 0; i--)
		{
			if (chessboard[i][j] != 0)
			{
				temp2 = chessboard[i][j];
				chessboard[i][j] = 0;
				chessboard[count2][j] = temp2;
				count2--;
			}
		}
}
void game::left()
{
	temp1 = count1;
	flag = false;
	for (i = 0; i < m; i++)
		for (j = 0; j < 4;)
		{
			for (; j < 4 && chessboard[i][j] == 0; j++); // 找非零值
			if (j == 4)
				break;
			else
			{
				for (k = j + 1; k < 4 && chessboard[i][k] == 0; k++);//找下一个非零值
				if (k == 4)
					break;
				else if (chessboard[i][j] == chessboard[i][k])//匹配
				{
					chessboard[i][j] *= 2;
					chessboard[i][k] = 0;
					j = k + 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[i][k])//不匹配
				{
					j = k;
				}
			}
		}
	for (i = 0; i < m; i++)
		for (j = 0, count2 = 0; j < 4; j++)
		{
			if (chessboard[i][j] != 0)
			{
				temp2 = chessboard[i][j];
				chessboard[i][j] = 0;
				chessboard[i][count2] = temp2;
				count2++;
			}
		}
}
void game::right()
{
	temp1 = count1;
	flag = false;
	for (i = 0; i < m; i++)
		for (j = m - 1; j >= 0;)
		{
			for (; j >= 0 && chessboard[i][j] == 0; j--); // 找非零值
			if (j == -1)
				break;
			else
			{
				for (k = j - 1; k >= 0 && chessboard[i][k] == 0; k--);//找下一个非零值
				if (k == -1)
					break;
				else if (chessboard[i][j] == chessboard[i][k])//匹配
				{
					chessboard[i][j] *= 2;
					chessboard[i][k] = 0;
					j = k - 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[i][k])//不匹配
				{
					j = k;
				}
			}
		}
	for (i = 0; i < m; i++)
		for (j = m - 1, count2 = m - 1; j >= 0; j--)
		{
			if (chessboard[i][j] != 0)
			{
				temp2 = chessboard[i][j];
				chessboard[i][j] = 0;
				chessboard[i][count2] = temp2;
				count2--;
			}
		}
}
bool game::gameover()
{
	if (flag)
		count1++;//判连消
	if (temp1 == count1)
		count1 = 0;//未消除，连消归零
	add(count1);
	for (i = m - 1, j = 0; j < m; j++)//最后一行
	{
		if (j == m - 1)//右下角
		{
			if (chessboard[i][j] == 0)
				return false;
			else if (chessboard[i][j] == 2048)
			{
				cout << "You Win~\n";
				return true;
			}
		}
		else
		{
			if (chessboard[i][j] == 0 || chessboard[i][j] == chessboard[i][j + 1])
				return false;
			else if (chessboard[i][j] == 2048)
			{
				cout << "You Win~\n";
				return true;
			}
		}
	}
	for (i = 0, j = m - 1; i < m; i++)//最后一列
	{
		if (i == m - 1)//右下角
		{
			if (chessboard[i][j] == 0)
				return false;
			else if (chessboard[i][j] == 2048)
			{
				cout << "You Win~\n";
				return true;
			}
		}
		else
		{
			if (chessboard[i][j] == 0 || chessboard[i][j] == chessboard[i + 1][j])
				return false;
			else if (chessboard[i][j] == 2048)
			{
				cout << "You Win~\n";
				return true;
			}
		}
	}
	for (i = 0; i < m - 1; i++)
		for (j = 0; j < m - 1; j++)
		{
			if (chessboard[i][j] == 2048)
			{
				cout << "You Win!\n";
				return true;
			}
			else if (chessboard[i][j] == chessboard[i][j + 1] || chessboard[i][j] == chessboard[i + 1][j] || chessboard[i][j] == 0)
				return false;
		}
	cout << "Game over.\n";
	return true;
}
int main() {
	char move = ' ';
	game game2048;
	do {
		cin >> move;
		switch (move)
		{
		case 'W':system("cls"); game2048.up(); if (game2048.gameover()) return 0; else break;
		case 'S':system("cls"); game2048.down(); if (game2048.gameover()) return 0; else  break;
		case 'A':system("cls"); game2048.left(); if (game2048.gameover()) return 0; else  break;
		case 'D':system("cls"); game2048.right(); if (game2048.gameover()) return 0; else  break;
		case 'E':
		{
			printf("确定要退出吗？\nY.是 N.否\n");
			cin >> move;
			if (move == 'N')
			{
				system("cls"); game2048.showchessboard();
				break;
			}
			else if (move == 'Y') return 0;
			else printf("输入错误，请重新输入：\n"); break;
		}
		default:printf("输入错误，请重新输入：\n"); break;
		}
	} while (1);
}