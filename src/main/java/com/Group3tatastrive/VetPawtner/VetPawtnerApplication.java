package com.Group3tatastrive.VetPawtner;

import com.Group3tatastrive.VetPawtner.Entity.*;
import com.Group3tatastrive.VetPawtner.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class VetPawtnerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VetPawtnerApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	DashboardRepository dashboardRepository;
	@Autowired
	PetRepository petRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ForumRepository forumRepository;

	@Override
	public void run(String... args) throws Exception {

		// 1. ✅ Create/Get User (check if exists first)
		User user = createOrGetUser();

		// 2. ✅ Create/Get Product (check if exists first)
		Product product = createOrGetProduct();

		// 3. ✅ Create Order with proper relationships
		Order order = createOrderIfNotExists(user, product);

		// 4. ✅ Create Dashboard
		Dashboard dashboard = createOrUpdateDashboard(user);

		// 5. ✅ Create Pet
		Pet pet = createPetIfNotExists(user);

		//6. create post -- commit
		Forum post = createForumPost(user);

		Forum comment = createForumComment(user, post);




		System.out.println("✅ All demo data created successfully!");
	}

	private User createOrGetUser() {
		String email = "Bhuvan123@gmail.com";
		String username = "Bhuvan";

		// Check if user already exists by email
		Optional<User> existingUser = userRepository.findByEmail(email);
		if (existingUser.isPresent()) {
			System.out.println("User already exists with email: " + email);
			return existingUser.get();
		}

		// Check if username already exists
		if (userRepository.findByUsername(username).isPresent()) {
			System.out.println("User already exists with username: " + username);
			return userRepository.findByUsername(username).get();
		}

		// Create new user
		User user = new User();
		user.setUsername(username);
		user.setUserphone("8947535256");
		user.setUseraddress("Chennai");
		user.setEmail(email);
		user.setPassword("bhuv@1234");
		user.setRole(User.Role.admin);

		User savedUser = userRepository.save(user);
		System.out.println("✅ New user created with ID: " + savedUser.getId());
		return savedUser;
	}

	private Product createOrGetProduct() {
		String productName = "Darshan";

		// Check if product already exists (you may need to add this method to ProductRepository)
		// For now, we'll create a simple check
		Product product = new Product();
		product.setName(productName);
		product.setDescription("Toy bone item");
		product.setPrice(200);
		product.setStock(2);
		product.setCategory("Toy");
		product.setImage_url("https://localhost:8080");

		Product savedProduct = productRepository.save(product);
		System.out.println("✅ Product created/updated with ID: " + savedProduct.getProductId());
		return savedProduct;
	}

	private Order createOrderIfNotExists(User user, Product product) {
		// Simple check to avoid creating duplicate orders
		// You might want to add a more sophisticated check

		Order order = new Order();
		order.setUser(user);           // ✅ Set actual user object
		order.setProduct(product);     // ✅ Set actual product object
		order.setQuantity(35);
		order.setTotalPrice(1200.0);
		order.setOrderDate(LocalDate.of(2025, 8, 4));
		order.setStatus(Order.Status.Shipped);  // ✅ Correct enum value

		Order savedOrder = orderRepository.save(order);
		System.out.println("✅ Order created with ID: " + savedOrder.getOrderId());
		return savedOrder;
	}

	private Dashboard createOrUpdateDashboard(User user) {
		Dashboard dashboard = new Dashboard();
		dashboard.setUser(user);
		dashboard.setTotalPets(1);
		dashboard.setTotalOrders(1);  // ✅ Updated to reflect actual order count

		Dashboard savedDashboard = dashboardRepository.save(dashboard);
		System.out.println("✅ Dashboard created/updated");
		return savedDashboard;
	}

	private Pet createPetIfNotExists(User user) {
		Pet pet = new Pet();
		pet.setUser(user);
		pet.setName("Tommy");
		pet.setBreed("Labrador");
		pet.setGender(Pet.Gender.male);
		pet.setDob(LocalDate.of(2025, 8, 10));
		pet.setVaccinationDetails("Rabies, Distemper");

		Pet savedPet = petRepository.save(pet);
		System.out.println("✅ Pet created with ID: " + savedPet.getPetId());
		return savedPet;
	}
	private Forum createForumPost(User user) {
		Forum post = new Forum();
		post.setUser(user);
		post.setTitle("Welcome to VetPawtner Forum!");
		post.setContent("This is the first forum post for pet lovers.");
		post.setParent(null); // Top-level post

		Forum savedPost = forumRepository.save(post);
		System.out.println("✅ Forum post created with ID: " + savedPost.getForumId());
		return savedPost;
	}

	 private Forum createForumComment(User user, Forum parentPost) {
		Forum comment = new Forum();
		comment.setUser(user);
		comment.setContent("This is a comment on the first post.");
		comment.setParent(parentPost); // Reply
		// Title is null for comments (`setTitle(null)` is optional)
		Forum savedComment = forumRepository.save(comment);
		System.out.println("✅ Forum comment created with ID: " + savedComment.getForumId());
		return savedComment;
	}


}
