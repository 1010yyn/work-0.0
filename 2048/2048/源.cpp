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
	}//�����ʼ����
	void add(int count1);//��������
	void showchessboard();//��ʾ����
	void up();//����
	void down();//����
	void left();//����
	void right();//����
	bool gameover();//��Ϸʧ��
private:
	int chessboard[m][m];
	int x, y, count1, count2, temp1, temp2, k;//c1-������c2-��λ��ǣ�t1-��������t2,k-��ʱ����
	bool flag;//����
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
	printf("����������������������\n");
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
	printf("����������������������\n");
	printf("Tips:\n1.W��������\n2.S��������\n3.A��������\n4.D��������\n5.E�����˳�\n������ָ�\n");
}
void game::up()
{
	temp1 = count1;
	flag = false;
	for (j = 0; j < m; j++)
		for (i = 0; i < m;)
		{
			for (; i < 4 && chessboard[i][j] == 0; i++); // �ҷ���ֵ
			if (i == 4)
				break;
			else
			{
				for (k = i + 1; k < 4 && chessboard[k][j] == 0; k++);//����һ������ֵ
				if (k == 4)
					break;
				else if (chessboard[i][j] == chessboard[k][j])//ƥ��
				{
					chessboard[i][j] *= 2;
					chessboard[k][j] = 0;
					i = k + 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[k][j] && k < 4)//��ƥ��
				{
					i = k;
				}
			}
		}
	for (j = 0; j < m; j++)//��������
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
			for (; i >= 0 && chessboard[i][j] == 0; i--); // �ҷ���ֵ
			if (i == -1)
				break;
			else
			{
				for (k = i - 1; k >= 0 && chessboard[k][j] == 0; k--);//����һ������ֵ
				if (k == -1)
					break;
				else if (chessboard[i][j] == chessboard[k][j])//ƥ��
				{
					chessboard[i][j] *= 2;
					chessboard[k][j] = 0;
					i = k - 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[k][j])//��ƥ��
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
			for (; j < 4 && chessboard[i][j] == 0; j++); // �ҷ���ֵ
			if (j == 4)
				break;
			else
			{
				for (k = j + 1; k < 4 && chessboard[i][k] == 0; k++);//����һ������ֵ
				if (k == 4)
					break;
				else if (chessboard[i][j] == chessboard[i][k])//ƥ��
				{
					chessboard[i][j] *= 2;
					chessboard[i][k] = 0;
					j = k + 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[i][k])//��ƥ��
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
			for (; j >= 0 && chessboard[i][j] == 0; j--); // �ҷ���ֵ
			if (j == -1)
				break;
			else
			{
				for (k = j - 1; k >= 0 && chessboard[i][k] == 0; k--);//����һ������ֵ
				if (k == -1)
					break;
				else if (chessboard[i][j] == chessboard[i][k])//ƥ��
				{
					chessboard[i][j] *= 2;
					chessboard[i][k] = 0;
					j = k - 1;
					flag = true;
				}
				else if (chessboard[i][j] != chessboard[i][k])//��ƥ��
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
		count1++;//������
	if (temp1 == count1)
		count1 = 0;//δ��������������
	add(count1);
	for (i = m - 1, j = 0; j < m; j++)//���һ��
	{
		if (j == m - 1)//���½�
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
	for (i = 0, j = m - 1; i < m; i++)//���һ��
	{
		if (i == m - 1)//���½�
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
			printf("ȷ��Ҫ�˳���\nY.�� N.��\n");
			cin >> move;
			if (move == 'N')
			{
				system("cls"); game2048.showchessboard();
				break;
			}
			else if (move == 'Y') return 0;
			else printf("����������������룺\n"); break;
		}
		default:printf("����������������룺\n"); break;
		}
	} while (1);
}