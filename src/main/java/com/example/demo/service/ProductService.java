package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDetails;
import com.example.demo.model.Supplier;
import com.example.demo.model.dto.ProductRequestDTO;
import com.example.demo.model.dto.ProductResponseDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductDetailsRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    private final CategoryRepository categoryRepository;

    private final SupplierRepository supplierRepository;

    private final ProductDetailsRepository productDetailsRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository, SupplierRepository supplierRepository, ProductDetailsRepository productDetailsRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productDetailsRepository = productDetailsRepository;
    }

    public List<ProductResponseDTO> getAll(){


        return  repository.findAll()
                .stream().map(this::toResponseDTO)
                .toList();
    }

    public List<ProductResponseDTO> findByPriceBetween(Double min, Double max){
        return  repository.findByPriceBetween(min,max)
                .stream().map(this::toResponseDTO)
                .toList();
    }

    public Optional<ProductResponseDTO> getById(int id){
        if(repository.findById(id).isPresent()){
            return Optional.of(toResponseDTO(repository.findById(id).get()));
        } else return Optional.empty();

    }

    public List<ProductResponseDTO> searchByName(String name){
        return repository.searchByName(name)
                .stream()
                .map(product -> toResponseDTO(product))
                .toList();

    }
    public List<ProductResponseDTO> findCustom(String name){
        return repository.searchByCategoryName(name)
                .stream()
                .map(product -> toResponseDTO(product))
                .toList();

    }

    public List<ProductResponseDTO> findByName(String name){
        return repository.findByName(name)
                .stream()
                .map(product -> toResponseDTO(product))
                .toList();

    }
    public List<ProductResponseDTO> findByCategory(String name){
        return repository.findByCategoryName(name)
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    public ProductResponseDTO addProduct(ProductRequestDTO dto){
        Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        Set<Supplier> suppliers = dto.getSupplierIds() != null
                ? new HashSet<>(supplierRepository.findAllById(dto.getSupplierIds()))
                : new HashSet<>();


        Product p = toEntity(dto,category,suppliers);

        return toResponseDTO(repository.save(p));
    }



    public boolean deleteById(int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void addInternalRating(int id, String internalRating){
        Optional<Product> existing = repository.findById(id);
        if(existing.isPresent()){
            existing.get().setInternalRating(internalRating);
        }
    }

    public ProductResponseDTO updateProduct(int id, ProductRequestDTO productDTO){

        Optional<Product> existing = repository.findById(id);

        if (existing.isPresent()){
            existing.get().setName(productDTO.getName());
            existing.get().setPrice(productDTO.getPrice());
            existing.get().setInternalRating(productDTO.getInternalRating());
            repository.save(existing.get());
            return toResponseDTO(existing.get());
        }

        return null;
    }



    public ProductResponseDTO patchProduct(int id, ProductRequestDTO product){
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()){
            if(product.getName() != null){
                existing.get().setName(product.getName());
            }
            if(product.getPrice() != null){
                existing.get().setPrice(product.getPrice());
            }
            if (product.getInternalRating()!= null){
                existing.get().setInternalRating(product.getInternalRating());
            }
            repository.save(existing.get());
            return  toResponseDTO(existing.get());
        }
        return null;

    }

    public ProductResponseDTO toResponseDTO(Product product){
        String category = product.getCategory() != null ? product.getCategory().getName() : null;
        Set<String> suppliers = product.getSuppliers() != null
                ? product.getSuppliers().stream().map(Supplier::getName).collect(Collectors.toSet())
                : Set.of();

        return new ProductResponseDTO(product.getId(),
                product.getName(),
                product.getPrice(),
                category,
                suppliers,
                product.getDetails());
    }

    public Product toEntity(ProductRequestDTO request,
                             Category category,
                             Set<Supplier> suppliers){
       Product product = new Product();

       if (request.getName() != null){
           product.setName(request.getName());
       }
       if(request.getPrice() != null){
           product.setPrice(request.getPrice());
       }

       if (request.getInternalRating() != null){
           product.setInternalRating(request.getInternalRating());

       }
       if(category != null){
           product.setCategory(category);
       }
       product.setSuppliers(suppliers);

       if(request.getManufacturer() != null || request.getDetailsDescription() != null){
          ProductDetails details = new ProductDetails();
          details.setManufacturer(request.getManufacturer());
          details.setDescription(request.getDetailsDescription());
          details.setProduct(product);
          product.setDetails(details);
       }


       return product;

    }


}
