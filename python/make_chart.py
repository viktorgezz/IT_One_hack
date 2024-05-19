import matplotlib.pyplot as plt


class MakeChart(object):

    def __init__(self, labels, sizes):
        self.labels = labels
        self.sizes = sizes
        self.colors = ['gold', 'yellowgreen', 'lightcoral', 'red', "green", "yellow", "blue", 'lightblue']


        self.make_chart_png()


    def make_chart_png(self):
        plt.pie(self.sizes, labels=self.labels, colors=self.colors, autopct='%1.1f%%', shadow=False, startangle=100)
        plt.axis('equal')
        plt.savefig('pie_chart.png')



mc = MakeChart(["w", "z", "x"], [5, 4, 6])


