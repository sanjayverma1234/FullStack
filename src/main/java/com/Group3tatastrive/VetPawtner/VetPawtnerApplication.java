package com.Group3tatastrive.VetPawtner;

import com.Group3tatastrive.VetPawtner.Entity.*;
import com.Group3tatastrive.VetPawtner.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@SpringBootApplication
@EntityScan("com.Group3tatastrive.VetPawtner.Entity")
public abstract class VetPawtnerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VetPawtnerApplication.class, args);
	}

	@Autowired UserRepository userRepository;
	@Autowired OrderRepository orderRepository;
	@Autowired DashboardRepository dashboardRepository;
	@Autowired PetRepository petRepository;
	@Autowired ProductRepository productRepository;
	@Autowired ForumRepository forumRepository;
	@Autowired PetHealthRepository petHealthRepository;
	@Autowired EventRepository eventRepository;
	@Autowired FundingRepository fundingRepository;
	@Autowired PaymentRepository paymentRepository;
	@Autowired PetClinicRepository petClinicRepository;
	@Autowired AppointmentRepository appointmentRepository;
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

		// 6. ✅ Create Forum Post
		Forum post = createForumPost(user);

		// 7. ✅ Create Pet Health Record
		PetHealth pet1 = createPetHealth(pet);

		// 8. ✅ Create Forum Comment
		Forum comment = createForumComment(user, post);

		Event event = createEvent(user);

		Funding fund = createFunding(user);

		Payment payment = createPayment(user,fund);

		PetClinic petClinic = createPetclinic(user,payment);


		System.out.println("✅ All demo data created successfully!");
	}

	private Forum createForumPost(User user) {
		Forum post = new Forum();
		post.setUser(user);
		post.setTitle("Welcome to the Vet Pawtner Forum!");
		post.setContent("This is the very first post in the forum.");
		post.setParent(null); // Root post

		Forum savedPost = forumRepository.save(post);
		System.out.println("✅ Forum post created with ID: " + savedPost.getForumId());
		return savedPost;
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

		// TODO: add repository `findByName()` check if needed
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
		Order order = new Order();
		order.setUser(user);
		order.setProduct(product);
		order.setQuantity(35);
		order.setTotalPrice(1200.0);
		order.setOrderDate(LocalDate.of(2025, 8, 4));
		order.setStatus(Order.Status.Shipped);
		Order savedOrder = orderRepository.save(order);
		System.out.println("✅ Order created with ID: " + savedOrder.getOrderId());
		return savedOrder;
	}

	private Dashboard createOrUpdateDashboard(User user) {
		Dashboard dashboard = new Dashboard();
		dashboard.setUser(user);
		dashboard.setTotalPets(1);
		dashboard.setTotalOrders(1);

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

	private Forum createForumComment(User user, Forum parentPost) {
		Forum comment = new Forum();
		comment.setUser(user);
		comment.setContent("This is a comment on the first post.");
		comment.setTitle("Dogs Theory"); // optional
		comment.setParent(parentPost); // reply

		Forum savedComment = forumRepository.save(comment);
		System.out.println("✅ Forum comment created with ID: " + savedComment.getForumId());
		return savedComment;
	}

	private PetHealth createPetHealth(Pet pet) {
		PetHealth petHealth = new PetHealth();
		petHealth.setPet(pet);
		petHealth.setCheckup_date(LocalDate.now());
		petHealth.setWeight(new BigDecimal("12.50"));
		petHealth.setTemperature(new BigDecimal("38.5"));
		petHealth.setNotes("Routine check-up, healthy.");
		petHealth.setVet_name("Dr. Smith");

		petHealthRepository.save(petHealth);
		System.out.println("✅ Pet health record created successfully for pet ID: " + pet.getPetId());
		return petHealth;
	}

	private Event createEvent(User user){
		Event event = new Event();
		event.setUser(user);
		//event.setEvent_id(1);
		event.setEvent_name("Dogs Event");
		event.setPrice(5623145d);
		event.setDate("12-09-2025");
		event.setTime("12:56:32");
		event.setLocation("Bangalore");
		eventRepository.save(event);
		System.out.println("The Event Records are created successfully with Event Id: "+event.getEvent_id());
		return event;
	}

	private Funding createFunding(User user){
		Funding fund = new Funding();
		//fund.setFunding_id(1);
		fund.setAmount(25000d);
		fund.setFunding_type("NGOs");
		fund.setUser(user);
		fund.setPurpose("Street Dog Fleeding");
		fund.setDate_received("15-09-2025");
		fund.setStatus("Paid");
		fundingRepository.save(fund);
		System.out.println("The Funding Records are created successfully funding Id " + fund.getFunding_id());
		return fund;
	}
	private Payment createPayment(User user,Funding funding){
		Payment payment = new Payment();
		//payment.setPayment_id(1);
		payment.setAmount(funding.getAmount());
		payment.setStatus("paid Successfully ");
		payment.setDate("15-09-2025");
		payment.setUser(user);
		payment.setFunding(funding);
		paymentRepository.save(payment);
		System.out.println("The Payment Records are created successfully with payment id " + payment.getPayment_id());
		return payment;
	}
	private PetClinic createPetclinic(User user,Payment payment) {
		PetClinic petClinic = new PetClinic();
		//petClinic.setCid(1);
		petClinic.setCname("sri krishna");
		petClinic.setCaddress("sanjaynagr");
		petClinic.setEmail("srikrishna@gmail.com");
		petClinic.setCphonenum("3456789234");
		petClinic.setOperatinghours(23);
		petClinic.setServiceoffered("consultation");
		petClinic.setPayemt(petClinic.getPayemt());
		petClinicRepository.save(petClinic);
		return petClinic;
	}

	private Appointment createAppointment(User user,PetClinic petClinic,Payment payment,Pet pet) {
		Appointment appointment = new Appointment();
		//appointment.setAid(1L);
		appointment.setUser(user);
		appointment.setPet(pet);
		appointment.setPetClinic(petClinic);
		appointment.setDoctorName("saraswathi");
		appointment.setAppointmentDate(LocalDate.of(2025, 9, 12));
		appointment.setAppointmentTime(LocalTime.of(12, 56, 32));
		appointmentRepository.save(appointment);
		return appointment;
	}
}