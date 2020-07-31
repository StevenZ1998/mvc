package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skillstorm.beans.Animal;

public class AnimalDAO implements AnimalDaoInterface{
	
	static Connection conn;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://dbskillstorm.cwqivbcy0lic.us-east-2.rds.amazonaws.com:3306/animals", "admin", "adminadmin");
			return conn;
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return conn;
	}

	@Override
	// id=0, name, color
	public Animal saveAnimal(Animal animal) {
		
		try {
			getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO animal (name, color) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, animal.getName());
			ps.setString(2, animal.getColor());
			
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			
			while(keys.next()) {
				int a_id = keys.getInt(1);
				System.out.println("Generated Key: " + a_id );
				animal.setAnimal_id(a_id);
			}
			

		}
		// BOILERPLATE CODE
			catch(SQLException e) {
					e.printStackTrace();
						/*try {
							conn.rollback(); // ROLLBACK
						}
						catch(SQLException e1) {
							e1.printStackTrace();
						}*/
				}
			finally {
					try {
						if(conn != null) {
							conn.close();
						}
					}
					catch (SQLException e) {
						e.printStackTrace();
					}	
				}
		
		
		return animal;
	}

	@Override
	public List<Animal> getAll() {
	List<Animal> animals = new ArrayList<Animal>();
		try {
			getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM animal");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				animals.add(new Animal(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
		}
		// BOILERPLATE CODE
					catch(SQLException e) {
							e.printStackTrace();
								/*try {
									conn.rollback(); // ROLLBACK
								}
								catch(SQLException e1) {
									e1.printStackTrace();
								}*/
						}
					finally {
							try {
								if(conn != null) {
									conn.close();
								}
							}
							catch (SQLException e) {
								e.printStackTrace();
							}	
						}
		return animals;
	}

	public Animal getAnimalById(int id) {
		Animal animal = null;
		try {
			getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM animal WHERE animal_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				animal = new Animal(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}
		// BOILERPLATE CODE
		catch(SQLException e) {
				e.printStackTrace();
					/*try {
						conn.rollback(); // ROLLBACK
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}*/
			}
		finally {
				try {
					if(conn != null) {
						conn.close();
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		return animal;
		
	}

	public void updateAnimal(Animal animalToUpdate) {
		try {
			getConnection();
			// Prepared Statement for update
			PreparedStatement stmt = conn.prepareStatement("UPDATE animal SET name = ?, color = ? WHERE animal_id = ?");
			stmt.setString(1, animalToUpdate.getName());
			stmt.setString(2, animalToUpdate.getColor());
			stmt.setInt(3, animalToUpdate.getAnimal_id());
			stmt.executeUpdate();
		}
		// BOILERPLATE CODE
		catch(SQLException e) {
				e.printStackTrace();
					/*try {
						conn.rollback(); // ROLLBACK
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}*/
			}
		finally {
				try {
					if(conn != null) {
						conn.close();
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}	
			}	
	}
		
	}

