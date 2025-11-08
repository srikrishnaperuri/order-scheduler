import java.util.*;

class Order {
    String id;
    int deadline;
    int profit;

    Order(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class OrderFulfillmentScheduler {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
            new Order("O1", 2, 100),
            new Order("O2", 1, 50),
            new Order("O3", 2, 10),
            new Order("O4", 1, 20),
            new Order("O5", 3, 70)
        );

        orders.sort((a, b) -> b.profit - a.profit);

        int maxDeadline = orders.stream().mapToInt(o -> o.deadline).max().getAsInt();
        String[] schedule = new String[maxDeadline + 1];
        int totalProfit = 0;

        for (Order order : orders) {
            for (int d = order.deadline; d > 0; d--) {
                if (schedule[d] == null) {
                    schedule[d] = order.id;
                    totalProfit += order.profit;
                    break;
                }
            }
        }

        System.out.println("Orders scheduled: " + Arrays.toString(schedule));
        System.out.println("Total Profit: $" + totalProfit);
    }
}
