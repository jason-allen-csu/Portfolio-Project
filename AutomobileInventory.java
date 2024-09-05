import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Automobile {
	private String make;
	private String model;
	private String color;
	private int year;
	private int mileage;
	private double price;
	
	public Automobile() {
		this.make = "TBD";
		this.model = "TBD";
		this.color = "TBD";
		this.year = 1900;
		this.mileage = 0;
		this.price = 0.0;
	}
	
	public Automobile(String make, String model, String color, int year, int mileage, double price) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
	}
	
	public String[] detailAutomobile() {
		try {
			String[] detail = {make, model, color, String.valueOf(year), String.valueOf(mileage), String.valueOf(price)};
			
			return detail;
		
		} catch (Exception e) {
			return new String[] {"Unable to display due to:" +e.getMessage()};
		}
	}
	
	public String addAutomobile(String make, String model, String color, int year, int mileage, double price) {
		try {
			this.make = make;
			this.model = model;
			this.color = color;
			this.year = year;
			this.mileage = mileage;
			this.price = price;
			
			return "Vehicle added to inventory.";
			
		} catch (Exception e) {
			return "Vehicle not added due to: " +e.getMessage();
		}	
	}

	public String removeAutomobile() {
		try {
			this.make = "";
			this.model = "";
			this.color = "";
			this.year = 0;
			this.mileage = 0;
			this.price = 0.0;
			
			return "Vehicle removed from inventory.";
		
		} catch (Exception e) {
			return "Vehicle not removed due to: " +e.getMessage();
		}
	}
	
	public String updateAutomobile(String make, String model, String color, int year, int mileage, double price) {
		try {
			this.make = make;
			this.model = model;
			this.color = color;
			this.year = year;
			this.mileage = mileage;
			this.price = price;
			
			return "Vehicle details updated.";
			
		} catch (Exception e) {
			return "Vehicle not updated due to: " +e.getMessage();
		}		
	}
}

class InventorySystem{

	private ArrayList<Automobile> vehicle = new ArrayList<>();
	
	public static void main(String[] args) {
		InventorySystem inventory = new InventorySystem();
		Scanner scnr = new Scanner(System.in);
		boolean exit = false;
		
		while(!exit) {
			System.out.println("\nAutomobile Inventory Management System Menu");
			System.out.println("1. List all Automobile");
			System.out.println("2. Add Automobile");
			System.out.println("3. Remove Automobile");
			System.out.println("4. Update Automobile details");
			System.out.println("5. Exit");
			System.out.println("Enter your choice (1-5)");
			
			int choice = scnr.nextInt();
			scnr.nextLine();
			
			switch(choice) {
				case 1:
					inventory.listAutomobile();
					break;
					
				case 2:
					System.out.println("Enter automobile make:");
					String make = scnr.nextLine();
					System.out.println("Enter automobile model:");
					String model = scnr.nextLine();
					System.out.println("Enter automobile color:");
					String color = scnr.nextLine();
					System.out.println("Enter automobile year:");
					int year = scnr.nextInt();
					System.out.println("Enter automobile mileage:");
					int mileage = scnr.nextInt();
					System.out.println("Enter automobile price:");
					double price = scnr.nextDouble();
					scnr.nextLine();
					
					Automobile newInventory = new Automobile();
					String addFunction = newInventory.addAutomobile(make, model, color, year, mileage, price);
					inventory.vehicle.add(newInventory);
					System.out.println(addFunction);
					break;
			
				case 3:
					if(inventory.vehicle.isEmpty()) {
						System.out.println("No Automobile in inventory to remove.");
					}
					else {
						inventory.listAutomobile();
						System.out.println("Enter Vehicle ID to remove: ");
						int vehicleID = scnr.nextInt();
						scnr.nextLine();
						int removeIndex = vehicleID - 1;
						
						if(removeIndex >= 0 && removeIndex < inventory.vehicle.size()) {
							String removeFunction = inventory.vehicle.get(removeIndex).removeAutomobile();						
							inventory.vehicle.remove(removeIndex);
							System.out.println(removeFunction);
						} 
						else {
							System.out.println("Invalid Index");
						}
					}
					break;
				
				case 4:
					if(inventory.vehicle.isEmpty()) {
						System.out.println("No Automobile in inventory to update.");
					}
					else {
						inventory.listAutomobile();
						System.out.println("Enter Vehicle ID to update: ");
						int vehicleID = scnr.nextInt();
						scnr.nextLine();
						int updateIndex = vehicleID - 1;
						
						if(updateIndex >= 0 && updateIndex < inventory.vehicle.size()) {
							System.out.println("Enter automobile make:");
							String upMake = scnr.nextLine();
							System.out.println("Enter automobile model:");
							String upModel = scnr.nextLine();
							System.out.println("Enter automobile color:");
							String upColor = scnr.nextLine();
							System.out.println("Enter automobile year:");
							int upYear = scnr.nextInt();
							System.out.println("Enter automobile mileage:");
							int upMileage = scnr.nextInt();
							System.out.println("Enter automobile price:");
							double upPrice = scnr.nextDouble();
							scnr.nextLine();
							
							String updateFunction = inventory.vehicle.get(updateIndex).updateAutomobile(upMake, upModel, upColor, upYear, upMileage, upPrice);
							System.out.println(updateFunction);
						}
						else {
							System.out.println("Invalid Index");
						}
					}
					break;
					
				case 5:
					exit = true;
					break;
					
				default:
					System.out.println("Invalid menu option. Please try again.");
					break;
			}
		}
		
		System.out.println("Do you want to transfer Inventory list to text file ? (Y/N)");
		String response = scnr.nextLine(); 
		try {
			if(response.equalsIgnoreCase("Y")) {
				inventory.fileTransfer();
			}
			else if(response.equalsIgnoreCase("N")) {
				System.out.println("Not printing to text file");
			} 
		} catch(Exception e) {
				System.out.println("Invalid due to:" +e.getMessage());
			}
		scnr.close();
		}
	
	public void listAutomobile() {
		try {
			if (vehicle.isEmpty()) {
				System.out.println("Empty inventory system.");
			}
			else {
				for (int i=0; i < vehicle.size(); i++) {
					String[] data = vehicle.get(i).detailAutomobile();
					System.out.println("Automobile ID:" +(i+1));
					for (String detail : data) {
						System.out.print(detail + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to print inventory due to:" +e.getMessage());
		}
	}
	
	public void fileTransfer() {
		try(FileWriter writer = new FileWriter("Inventory.txt")){
			for(int i = 0; i < vehicle.size(); i++) {
				String[] data = vehicle.get(i).detailAutomobile();
				writer.write("Automobile ID:" +(i+1));
				for (String detail : data) {
					writer.write(detail + " ");
				}
				writer.write("\n");
			}
			System.out.println("Inventory information printed to text file");
		} catch (IOException e) {
			System.out.println("Failed to print to file due to:" +e.getMessage());
		}
	}	
}

